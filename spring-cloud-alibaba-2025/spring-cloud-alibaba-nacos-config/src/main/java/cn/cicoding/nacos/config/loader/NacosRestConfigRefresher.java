package cn.cicoding.nacos.config.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Dynamic refresh for Nacos config via periodic REST polling.
 *
 * Why polling instead of Nacos gRPC push:
 *   SCA 2025.1.0.0 + Nacos Client 3.x + Nacos 2.5.0 server has a known
 *   compatibility issue where:
 *     1. configService.getConfig() returns empty content
 *     2. configService.addListener() never invokes the callback
 *   The gRPC long-polling for change detection is broken.
 *
 * Workaround: poll the Nacos REST API on a fixed interval (default 5s),
 * compare the body with the last seen value, and on change:
 *   - replace the `nacosRestConfig` PropertySource
 *   - publish a RefreshEvent so @RefreshScope beans are recreated
 */
@Component
public class NacosRestConfigRefresher {

    private static final Logger log = LoggerFactory.getLogger(NacosRestConfigRefresher.class);

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private String serverAddr;
    private String dataId;
    private String group;

    /** Last successfully fetched body, used for change detection. */
    private final AtomicReference<String> lastContent = new AtomicReference<>("");

    @org.springframework.context.event.EventListener(
            org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void init() {
        this.serverAddr = environment.getProperty(
                "spring.cloud.nacos.config.server-addr", "127.0.0.1:8848");
        this.dataId = environment.getProperty("spring.application.name", "application")
                + "." + environment.getProperty(
                        "spring.cloud.nacos.config.file-extension", "properties");
        this.group = environment.getProperty(
                "spring.cloud.nacos.config.group", "DEFAULT_GROUP");

        // Seed with the current PropertySource content so we don't fire a
        // false-positive refresh on the first poll.
        Properties existing = readFromCurrentPropertySource();
        if (existing != null && !existing.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            existing.forEach((k, v) -> sb.append(k).append('=').append(v).append('\n'));
            lastContent.set(sb.toString());
        }
        log.info("[NacosRestConfigRefresher] Initialized: dataId={}, group={}, server={}, " +
                        "lastContent.length={}", dataId, group, serverAddr, lastContent.get().length());
    }

    /**
     * Poll every 5 seconds (configurable via spring.cloud.nacos.config.refresh-interval-ms).
     */
    @Scheduled(fixedDelayString = "${spring.cloud.nacos.config.refresh-interval-ms:5000}")
    public void poll() {
        if (serverAddr == null) {
            return; // init() not yet called
        }
        String url = String.format("http://%s/nacos/v1/cs/configs?dataId=%s&group=%s",
                serverAddr, dataId, group);
        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            int status = conn.getResponseCode();
            if (status != 200) {
                conn.disconnect();
                return;
            }
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            }
            conn.disconnect();

            String newContent = sb.toString();
            String oldContent = lastContent.get();

            if (newContent.equals(oldContent)) {
                return; // no change
            }

            log.info("[NacosRestConfigRefresher] Change detected ({} -> {} bytes). Refreshing...",
                    oldContent.length(), newContent.length());

            Properties props = new Properties();
            props.load(new java.io.StringReader(newContent));

            // Replace the PropertySource.
            if (environment.getPropertySources().get("nacosRestConfig") != null) {
                environment.getPropertySources().remove("nacosRestConfig");
            }
            environment.getPropertySources().addFirst(
                    new PropertiesPropertySource("nacosRestConfig", props));
            lastContent.set(newContent);

            // Fire RefreshEvent so @RefreshScope beans are recreated.
            eventPublisher.publishEvent(
                    new org.springframework.cloud.endpoint.event.RefreshEvent(
                            this, null, "nacos-rest-poll-refresh"));
            log.info("[NacosRestConfigRefresher] Refreshed. Keys: {}", props.stringPropertyNames());
        } catch (Exception e) {
            log.warn("[NacosRestConfigRefresher] Poll failed: {}", e.getMessage());
        }
    }

    private Properties readFromCurrentPropertySource() {
        org.springframework.core.env.PropertySource<?> ps =
                environment.getPropertySources().get("nacosRestConfig");
        if (ps instanceof PropertiesPropertySource pps) {
            Properties copy = new Properties();
            copy.putAll(pps.getSource());
            return copy;
        }
        return null;
    }
}
