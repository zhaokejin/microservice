package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConsumerFeginApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerFeginApplication.class, args);
  }
}
