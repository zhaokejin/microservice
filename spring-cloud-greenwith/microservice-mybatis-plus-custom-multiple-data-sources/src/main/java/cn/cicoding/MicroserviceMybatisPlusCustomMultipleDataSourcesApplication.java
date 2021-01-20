package cn.cicoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
@MapperScan("cn.cicoding.mapper")

public class MicroserviceMybatisPlusCustomMultipleDataSourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMybatisPlusCustomMultipleDataSourcesApplication.class, args);
    }

}
