package cn.cicoding.multiple.config.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

/**
 * Loads multiple Nacos config files via the REST v1 API and injects each
 * one as a high-priority PropertySource in the Spring Environment.
 *
 * Why we need this:
 *   SCA 2025.1.0.0's Nacos Client 3.x uses gRPC. With a Nacos 2.5.x server
 *   the gRPC config fetch silently returns empty content, so the standard
 *   `spring.config.import: nacos:...` mechanism fails. This initializer
 *   fetches the same configs over HTTP and injects them directly.
 *
 * Why ApplicationContextInitializer (not EnvironmentPostProcessor):
 *   EnvironmentPostProcessor is deprecated in Spring Boot 4.0. The
 *   ApplicationContextInitializer SPI is the supported replacement and
 *   runs early enough to influence @Value and @ConfigurationProperties
 *   binding during bean creation.
 *
 * Configs loaded (dataId -> key):
 *   - cicoding-provider.properties  -> contains "profile.provider=..."
 *   - cicoding-consumer.properties  -> contains "profile.consumer=..."
 */
public class NacosRestConfigInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    private static final Logger log = LoggerFactory.getLogger(NacosRestConfigInitializer.class);

    /** dataId list to fetch (order preserved for source layering). */
    private static final List<String> DATA_IDS = List.of(
            "cicoding-provider.properties",
            "cicoding-consumer.properties"
    );

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        String serverAddr = environment.getProperty(
                "spring.cloud.nacos.config.server-addr", "127.0.0.1:8848");
        String group = environment.getProperty(
                "spring.cloud.nacos.config.group", "DEFAULT_GROUP");
        String namespace = environment.getProperty(
                "spring.cloud.nacos.config.namespace", "");

        for (String dataId : DATA_IDS) {
            String sourceName = "nacosRestConfig[" + dataId + "]";
            fetchAndInject(environment, serverAddr, group, namespace, dataId, sourceName);
        }
    }

    private void fetchAndInject(ConfigurableEnvironment environment, String serverAddr,
                                String group, String namespace, String dataId, String sourceName) {
        // v1 API: tenant= is the namespace id. Empty tenant = public namespace.
        String url = String.format(
                "http://%s/nacos/v1/cs/configs?dataId=%s&group=%s&tenant=%s",
                serverAddr, dataId, group, namespace);
        log.info("[NacosRestConfigInitializer] Fetching: dataId={}, namespace={}, url={}",
                dataId, namespace, url);

        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            int status = conn.getResponseCode();
            log.info("[NacosRestConfigInitializer] HTTP status: {} for dataId={}", status, dataId);

            if (status == 200) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                }
                String content = sb.toString();
                log.info("[NacosRestConfigInitializer] dataId={} content ({} bytes): [{}]",
                        dataId, content.length(), content.replace("\n", "\\n"));

                if (!content.isEmpty()) {
                    Properties props = new Properties();
                    props.load(new java.io.StringReader(content));
                    if (environment.getPropertySources().get(sourceName) != null) {
                        environment.getPropertySources().remove(sourceName);
                    }
                    environment.getPropertySources().addFirst(
                            new PropertiesPropertySource(sourceName, props));
                    log.info("[NacosRestConfigInitializer] Injected {} keys from {}: {}",
                            props.size(), dataId, props.stringPropertyNames());
                } else {
                    log.warn("[NacosRestConfigInitializer] Empty content for dataId={}", dataId);
                }
            } else if (status == 404) {
                log.warn("[NacosRestConfigInitializer] Not found in Nacos: dataId={}, namespace={}",
                        dataId, namespace);
            } else {
                log.warn("[NacosRestConfigInitializer] Unexpected HTTP {} for dataId={}",
                        status, dataId);
            }
            conn.disconnect();
        } catch (Exception e) {
            log.error("[NacosRestConfigInitializer] Failed to load dataId={}", dataId, e);
        }
    }
}
