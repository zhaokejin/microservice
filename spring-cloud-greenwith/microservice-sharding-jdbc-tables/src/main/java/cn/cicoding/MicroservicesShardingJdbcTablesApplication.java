package cn.cicoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ShardingSphere使用取模方式，不分库，只分表案例
 * @author zhaokejin
 */
@SpringBootApplication
public class MicroservicesShardingJdbcTablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesShardingJdbcTablesApplication.class, args);
    }

}
