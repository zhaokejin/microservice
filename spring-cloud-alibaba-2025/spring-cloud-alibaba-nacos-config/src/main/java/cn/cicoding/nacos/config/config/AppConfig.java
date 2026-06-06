package cn.cicoding.nacos.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Maps the `profile` property from Nacos config into a typed bean.
 * Nacos config is loaded by NacosRestConfigInitializer at startup and exposed
 * as a high-priority PropertySource in the Environment.
 */
@Data
@Component
@ConfigurationProperties
public class AppConfig {

    /** Maps to the 'profile' key in Nacos config. */
    private String profile = "moren";
}
