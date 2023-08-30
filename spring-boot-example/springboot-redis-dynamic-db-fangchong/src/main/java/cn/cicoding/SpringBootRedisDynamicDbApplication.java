package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaokejin
 * @description
 * @date 2021-09-05
 * description: springboot集成redis aop实现防重提交
 */
@SpringBootApplication
public class SpringBootRedisDynamicDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisDynamicDbApplication.class, args);
    }

}
