package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConsumerFeginFileApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerFeginFileApplication.class, args);
  }
}
