package com.cicoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cicoding.dao")
public class SecurityLoginDbAndQqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityLoginDbAndQqApplication.class, args);
	}
}
