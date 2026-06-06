package cn.cicoding.nacos.config.controller;

import cn.cicoding.nacos.config.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    /** Method 1: @Value injection - reads from Nacos via NacosRestConfigInitializer. */
    @Value("${profile:moren}")
    private String profile;

    /** Method 2: @ConfigurationProperties bean - reads from Nacos. */
    @Autowired
    private AppConfig appConfig;

    /** Read via @Value. */
    @GetMapping("/profile")
    public String hello() {
        return this.profile;
    }

    /** Read via @ConfigurationProperties. */
    @GetMapping("/profile/v2")
    public String helloV2() {
        return appConfig.getProfile();
    }
}
