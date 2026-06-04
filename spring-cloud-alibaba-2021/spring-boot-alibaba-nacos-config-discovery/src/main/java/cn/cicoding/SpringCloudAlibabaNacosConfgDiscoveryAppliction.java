package cn.cicoding;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaokejin
 * @description springboot工程注册到nacos中
 * @date 2021-09-03
 */
@SpringBootApplication
@NacosPropertySource(dataId = "data_json", autoRefreshed = true)
public class SpringCloudAlibabaNacosConfgDiscoveryAppliction {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaNacosConfgDiscoveryAppliction.class, args);
    }
}
