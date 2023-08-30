package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 在springboot中使用Guava基于令牌桶实现限流
 * https://www.cnblogs.com/kevinblandy/p/13435990.html
 * 原文：https://springboot.io/t/topic/2352
 */
@SpringBootApplication
public class SpringbootGuavaCurrentLimitingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGuavaCurrentLimitingApplication.class, args);
    }

}
