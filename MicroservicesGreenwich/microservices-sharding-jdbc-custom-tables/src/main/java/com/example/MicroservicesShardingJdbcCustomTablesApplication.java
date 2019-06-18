package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 不分库，只分表案例
 * @author zhaokejin
 */
@SpringBootApplication
public class MicroservicesShardingJdbcCustomTablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesShardingJdbcCustomTablesApplication.class, args);
    }

}
