package cn.cicoding.nacos.config;

import cn.cicoding.nacos.config.loader.NacosRestConfigInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudAlibabaNacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringCloudAlibabaNacosConfigApplication.class);
        // Register the Nacos REST config initializer explicitly.
        // (EnvironmentPostProcessor is deprecated in Spring Boot 4.0; the
        //  ApplicationContextInitializer SPI is loaded unreliably across
        //  4.x patch versions, so we register it programmatically for safety.)
        app.addInitializers(new NacosRestConfigInitializer());
        app.run(args);
    }
}
