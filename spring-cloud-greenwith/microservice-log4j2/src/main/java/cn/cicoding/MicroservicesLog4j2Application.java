package cn.cicoding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroservicesLog4j2Application {
    private static Logger log = LoggerFactory.getLogger(MicroservicesLog4j2Application.class);
    public static void main(String[] args) {
        SpringApplication.run(MicroservicesLog4j2Application.class, args);
        log.info("enter in entityParam");
    }

}
