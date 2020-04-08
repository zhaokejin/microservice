package com.cicoding;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
@RestController
public class SpringbootApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApolloApplication.class, args);
	}


}
