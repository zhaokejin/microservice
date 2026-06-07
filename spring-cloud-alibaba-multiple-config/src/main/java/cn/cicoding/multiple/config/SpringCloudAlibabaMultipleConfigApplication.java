package cn.cicoding.multiple.config;

import cn.cicoding.multiple.config.loader.NacosRestConfigInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // enables the @Scheduled poll loop in NacosRestConfigRefresher
public class SpringCloudAlibabaMultipleConfigApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringCloudAlibabaMultipleConfigApplication.class);
        // Register the Nacos REST config initializer explicitly to bypass the
        // SCA 2025 gRPC bug (which returns empty content for getConfig).
        // (EnvironmentPostProcessor is deprecated in Spring Boot 4.0; the
        //  ApplicationContextInitializer SPI is loaded unreliably across
        //  4.x patch versions, so we register it programmatically for safety.)
        app.addInitializers(new NacosRestConfigInitializer());
        app.run(args);
    }
}
