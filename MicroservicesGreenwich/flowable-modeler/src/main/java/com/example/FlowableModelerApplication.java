package com.example;

import org.flowable.ui.modeler.conf.ApplicationConfiguration;
import org.flowable.ui.modeler.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class, AppDispatcherServletConfiguration.class})
@SpringBootApplication
public class FlowableModelerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableModelerApplication.class, args);
    }

}
