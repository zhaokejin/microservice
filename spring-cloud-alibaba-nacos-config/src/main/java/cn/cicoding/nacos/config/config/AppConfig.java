package cn.cicoding.nacos.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Maps the `profile` property from Nacos config into a typed bean.
 * Nacos config is loaded by NacosRestConfigInitializer at startup and
 * refreshed dynamically by NacosRestConfigRefresher when Nacos pushes a
 * change event.
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties
public class AppConfig {

    /** Maps to the 'profile' key in Nacos config. */
    private String profile = "moren";
}
