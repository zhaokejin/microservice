package cn.cicoding.microservicesdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MicroservicesDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesDockerApplication.class, args);
    }

    @RestController
    public class HelloSimonController{
        @RequestMapping("/hello")
        public String hello(){
            return "hello world!";
        }
    }

}
