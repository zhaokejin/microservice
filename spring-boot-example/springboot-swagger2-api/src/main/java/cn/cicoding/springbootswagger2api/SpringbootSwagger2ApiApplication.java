package cn.cicoding.springbootswagger2api;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableSwagger2Doc
@SpringBootApplication
public class SpringbootSwagger2ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSwagger2ApiApplication.class, args);
    }

}
