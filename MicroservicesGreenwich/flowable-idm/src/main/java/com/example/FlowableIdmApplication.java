package com.example;

import org.flowable.ui.idm.conf.ApplicationConfiguration;
import org.flowable.ui.idm.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class, AppDispatcherServletConfiguration.class})
@SpringBootApplication
public class FlowableIdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableIdmApplication.class, args);
    }

}
