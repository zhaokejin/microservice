package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaProviderSkywalkingLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaProviderSkywalkingLogApplication.class, args);
    }

}
