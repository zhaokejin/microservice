package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudAlibabaConsumerFeignFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaConsumerFeignFileApplication.class, args);
    }

}
