package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MicroservicesConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesConfigServerApplication.class, args);
    }

}
