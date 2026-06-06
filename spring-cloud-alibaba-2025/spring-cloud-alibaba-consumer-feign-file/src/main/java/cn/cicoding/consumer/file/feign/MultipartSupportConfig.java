package cn.cicoding.consumer.file.feign;

import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartSupportConfig {

    @Bean
    public feign.codec.Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
}
