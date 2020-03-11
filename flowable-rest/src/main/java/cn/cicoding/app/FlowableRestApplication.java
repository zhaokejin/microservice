package cn.cicoding.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FlowableRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableRestApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer swaggerDocsConfigurer() {
       return new WebMvcConfigurer() {
            public void addViewControllers(ViewControllerRegistry registry)
            {
                registry.addViewController("/docs").setViewName("redirect:/docs/");
                registry.addViewController("/docs/").setViewName("forward:/docs/index.html");
            }
        };
    }
}
