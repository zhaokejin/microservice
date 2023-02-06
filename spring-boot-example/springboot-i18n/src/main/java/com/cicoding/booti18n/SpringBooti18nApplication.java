package com.cicoding.booti18n;

import com.cicoding.booti18n.common.MessageUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBooti18nApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBooti18nApplication.class, args);
        String message = MessageUtils.getMessage("mess.user.name");
        System.out.println("message{}" + message);
    }

}

