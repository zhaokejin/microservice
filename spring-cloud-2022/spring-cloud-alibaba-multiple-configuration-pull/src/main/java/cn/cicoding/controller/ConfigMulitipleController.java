package cn.cicoding.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaokejin
 * @description
 * @date 2021-08-31
 */
@RestController
@RefreshScope
@Slf4j
public class ConfigMulitipleController {

    @Value("${profile.provider}")
    private String cicodingProvider;

    @Value("${profile.consumer}")
    private String cicodingConsumer;

    @GetMapping("/profile")
    public String hello() {
        log.info(cicodingProvider);
        log.info(cicodingConsumer);
        return this.cicodingProvider + "<------>" + this.cicodingConsumer;
    }

}
