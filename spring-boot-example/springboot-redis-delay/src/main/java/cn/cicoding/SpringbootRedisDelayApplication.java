package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot+redis 监听key过期事件
 *
 * https://blog.csdn.net/qq_44695727/article/details/108125327
 */
@SpringBootApplication
public class SpringbootRedisDelayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisDelayApplication.class, args);
    }

}
