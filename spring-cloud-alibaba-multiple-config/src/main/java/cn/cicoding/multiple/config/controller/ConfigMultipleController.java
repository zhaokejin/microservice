package cn.cicoding.multiple.config.controller;

import cn.cicoding.multiple.config.config.MultiAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigMultipleController {

    // Method 1: @Value + @RefreshScope (traditional, needs refresh-enabled=true)
    @Value("${profile.provider:default-provider}")
    private String cicodingProvider;

    @Value("${profile.consumer:default-consumer}")
    private String cicodingConsumer;

    // Method 2: @ConfigurationProperties + @RefreshScope (recommended in Boot 4.x)
    @Autowired
    private MultiAppConfig multiAppConfig;

    /**
     * Read via @Value
     */
    @GetMapping("/profile")
    public String hello() {
        return this.cicodingProvider + "<------>" + this.cicodingConsumer;
    }

    /**
     * Read via @ConfigurationProperties (recommended)
     */
    @GetMapping("/profile/v2")
    public String helloV2() {
        return multiAppConfig.getProvider() + "<------>" + multiAppConfig.getConsumer();
    }
}
