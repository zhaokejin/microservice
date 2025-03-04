package com.example.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SentinelCurrentLimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelCurrentLimitingApplication.class, args);
    }
    @Slf4j
    @RestController
    static class TestController {

        @GetMapping("/hello")
        public String hello() {
            return "hello，sentinel-dashboard！";
        }

    }
}
