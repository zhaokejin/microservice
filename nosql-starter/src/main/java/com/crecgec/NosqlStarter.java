package com.crecgec;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.crecgec")
public class NosqlStarter {

    public static void main(String[] args) {
        SpringApplication.run(NosqlStarter.class, args);
    }

}
