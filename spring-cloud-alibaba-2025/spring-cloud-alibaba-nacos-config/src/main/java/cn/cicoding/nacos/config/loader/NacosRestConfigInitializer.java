package cn.cicoding.nacos.config.loader;

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
import java.util.Properties;

/**
 * Load Nacos config via REST API and inject it into the Spring Environment.
 *
 * Background: SCA 2025.1.0.0 ships Nacos Client 3.x which uses gRPC to talk to
 * Nacos 2.5.x servers. There is a known issue where the gRPC config fetch
 * silently returns empty content, so the standard `spring.config.import:
 * nacos:...` mechanism fails. This initializer works around the problem by
 * fetching the same config via the Nacos v1 HTTP REST API and adding it as a
 * high-priority PropertySource.
 *
 * The v1 API is used (not v2) because it returns the raw config body as
 * text/plain, avoiding JSON parsing.
 *
 * Registered via Spring Boot 4 SPI: META-INF/spring/org.springframework.context.ApplicationContextInitializer
 * (EnvironmentPostProcessor is deprecated in Spring Boot 4.0).
 */
public class NacosRestConfigInitializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext>, Ordered {

    private static final Logger log = LoggerFactory.getLogger(NacosRestConfigInitializer.class);

    /** Run as early as possible so our PropertySource precedes any others. */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String serverAddr = environment.getProperty(
                "spring.cloud.nacos.config.server-addr", "127.0.0.1:8848");
        String dataId = resolveDataId(environment);
        String group = environment.getProperty(
                "spring.cloud.nacos.config.group", "DEFAULT_GROUP");

        // v1 API: returns raw config body as text/plain. tenant= omitted -> default public namespace.
        String url = String.format("http://%s/nacos/v1/cs/configs?dataId=%s&group=%s",
                serverAddr, dataId, group);
        log.info("[NacosRestConfigInitializer] Fetching config: dataId={}, group={}, url={}",
                dataId, group, url);

        try {
            HttpURLConnection conn = (HttpURLConnection) URI.create(url).toURL().openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");

            int status = conn.getResponseCode();
            log.info("[NacosRestConfigInitializer] HTTP status: {}", status);

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
                log.info("[NacosRestConfigInitializer] Received {} bytes: [{}]",
                        content.length(), content.replace("\n", "\\n"));

                if (!content.isEmpty()) {
                    Properties props = new Properties();
                    props.load(new java.io.StringReader(content));
                    PropertiesPropertySource propertySource =
                            new PropertiesPropertySource("nacosRestConfig", props);
                    environment.getPropertySources().addFirst(propertySource);
                    log.info("[NacosRestConfigInitializer] Injected {} keys into Environment: {}",
                            props.size(), props.stringPropertyNames());
                } else {
                    log.warn("[NacosRestConfigInitializer] Config content is empty");
                }
            } else if (status == 404) {
                log.warn("[NacosRestConfigInitializer] Config not found (dataId={}, group={})",
                        dataId, group);
            } else {
                log.warn("[NacosRestConfigInitializer] Unexpected HTTP status: {}", status);
            }
            conn.disconnect();
        } catch (Exception e) {
            log.error("[NacosRestConfigInitializer] Failed to load config from Nacos", e);
        }
    }

    private String resolveDataId(ConfigurableEnvironment environment) {
        String appName = environment.getProperty("spring.application.name", "application");
        String fileExt = environment.getProperty(
                "spring.cloud.nacos.config.file-extension", "properties");
        return appName + "." + fileExt;
    }
}
