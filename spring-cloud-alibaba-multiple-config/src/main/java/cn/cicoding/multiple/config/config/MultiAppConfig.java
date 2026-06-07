package cn.cicoding.multiple.config.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Multi-config properties class using @ConfigurationProperties (recommended in Boot 4.x)
 * Reads profile.provider and profile.consumer from Nacos configs.
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "profile")
public class MultiAppConfig {

    /**
     * Maps to profile.provider in cicoding-provider.properties
     */
    private String provider = "default-provider";

    /**
     * Maps to profile.consumer in cicoding-consumer.properties
     */
    private String consumer = "default-consumer";
}
