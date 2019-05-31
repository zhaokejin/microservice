package com.example;

import org.flowable.ui.admin.conf.ApplicationConfiguration;
import org.flowable.ui.admin.conf.DispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ApplicationConfiguration.class, DispatcherServletConfiguration.class})
@SpringBootApplication
public class FlowableAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableAdminApplication.class, args);
    }

}
