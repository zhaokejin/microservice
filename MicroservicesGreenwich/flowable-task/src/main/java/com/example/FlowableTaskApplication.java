package com.example;

import org.flowable.ui.task.conf.ApplicationConfiguration;
import org.flowable.ui.task.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Import({ApplicationConfiguration.class, AppDispatcherServletConfiguration.class})
@SpringBootApplication
public class FlowableTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableTaskApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer workflow() {
        return new WebMvcConfigurer() {
            public void addViewControllers(@NonNull ViewControllerRegistry registry)
            {
                registry.addViewController("/workflow").setViewName("redirect:/workflow/");
                registry.addViewController("/workflow/").setViewName("forward:/workflow/index.html");
            }
        };
    }
}
