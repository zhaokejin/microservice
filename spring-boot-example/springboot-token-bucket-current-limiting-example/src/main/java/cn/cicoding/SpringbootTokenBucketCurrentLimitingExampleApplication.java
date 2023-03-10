package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jmeter测试SpringBoot+Redis令牌桶算法实现接口限流实例
 * https://blog.csdn.net/cs373616511/article/details/105093961
 */

@SpringBootApplication
public class SpringbootTokenBucketCurrentLimitingExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTokenBucketCurrentLimitingExampleApplication.class, args);
    }

}
