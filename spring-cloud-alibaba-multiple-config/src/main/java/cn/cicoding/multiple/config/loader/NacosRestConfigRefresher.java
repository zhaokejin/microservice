package cn.cicoding.multiple.config.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
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
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Polls each Nacos config via REST and triggers dynamic refresh on change.
 *
 * Why polling instead of Nacos gRPC push:
 *   SCA 2025.1.0.0 + Nacos Client 3.x + Nacos 2.5.0 has a known compatibility
 *   issue: configService.getConfig() returns empty content AND
 *   configService.addListener() never invokes the callback. So we poll the
 *   REST API and detect body changes ourselves.
 */
@Component
public class NacosRestConfigRefresher {

    private static final Logger log = LoggerFactory.getLogger(NacosRestConfigRefresher.class);

    private static final List<String> DATA_IDS = List.of(
            "cicoding-provider.properties",
            "cicoding-consumer.properties"
    );

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private String serverAddr;
    private String group;
    private String namespace;

    /** Last seen body per dataId, for change detection. */
    private final ConcurrentHashMap<String, String> lastContent = new ConcurrentHashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.serverAddr = environment.getProperty(
                "spring.cloud.nacos.config.server-addr", "127.0.0.1:8848");
        this.group = environment.getProperty(
                "spring.cloud.nacos.config.group", "DEFAULT_GROUP");
        this.namespace = environment.getProperty(
                "spring.cloud.nacos.config.namespace", "");

        // Seed the change-detection map with current PropertySource content
        // so the first poll does not fire a false-positive refresh.
        for (String dataId : DATA_IDS) {
            String sourceName = "nacosRestConfig[" + dataId + "]";
            Properties existing = readFromCurrentPropertySource(sourceName);
            if (existing != null && !existing.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                existing.forEach((k, v) -> sb.append(k).append('=').append(v).append('\n'));
                lastContent.put(dataId, sb.toString());
            } else {
                lastContent.put(dataId, "");
            }
        }
        log.info("[NacosRestConfigRefresher] Initialized for {} configs: {}",
                DATA_IDS.size(), DATA_IDS);
    }

    @Scheduled(fixedDelayString = "${spring.cloud.nacos.config.refresh-interval-ms:5000}")
    public void pollAll() {
        if (serverAddr == null) {
            return; // init not yet called
        }
        boolean anyChanged = false;
        for (String dataId : DATA_IDS) {
            if (pollOne(dataId)) {
                anyChanged = true;
            }
        }
        if (anyChanged) {
            eventPublisher.publishEvent(
                    new org.springframework.cloud.endpoint.event.RefreshEvent(
                            this, null, "nacos-rest-poll-refresh"));
            log.info("[NacosRestConfigRefresher] Published RefreshEvent");
        }
    }

    private boolean pollOne(String dataId) {
        String sourceName = "nacosRestConfig[" + dataId + "]";
        String url = String.format(
                "http://%s/nacos/v1/cs/configs?dataId=%s&group=%s&tenant=%s",
                serverAddr, dataId, group, namespace);
        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            int status = conn.getResponseCode();
            if (status != 200) {
                conn.disconnect();
                return false;
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
            String oldContent = lastContent.getOrDefault(dataId, "");

            if (newContent.equals(oldContent)) {
                return false; // no change
            }

            log.info("[NacosRestConfigRefresher] Change detected for {} ({} -> {} bytes)",
                    dataId, oldContent.length(), newContent.length());

            Properties props = new Properties();
            props.load(new java.io.StringReader(newContent));

            if (environment.getPropertySources().get(sourceName) != null) {
                environment.getPropertySources().remove(sourceName);
            }
            environment.getPropertySources().addFirst(
                    new PropertiesPropertySource(sourceName, props));
            lastContent.put(dataId, newContent);
            return true;
        } catch (Exception e) {
            log.warn("[NacosRestConfigRefresher] Poll failed for {}: {}", dataId, e.getMessage());
            return false;
        }
    }

    private Properties readFromCurrentPropertySource(String sourceName) {
        org.springframework.core.env.PropertySource<?> ps =
                environment.getPropertySources().get(sourceName);
        if (ps instanceof PropertiesPropertySource pps) {
            Properties copy = new Properties();
            copy.putAll(pps.getSource());
            return copy;
        }
        return null;
    }
}
