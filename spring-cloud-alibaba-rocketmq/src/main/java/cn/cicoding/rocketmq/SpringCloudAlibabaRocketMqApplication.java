package cn.cicoding.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaRocketMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaRocketMqApplication.class, args);
    }
}
