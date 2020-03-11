package com.cicoding.springbootapollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
@RestController
public class SpringbootApolloApplication {

	@Autowired
	private ConstantProperties constantProperties;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApolloApplication.class, args);
	}

	@RequestMapping("dc")
	public String SERVERPORT(){
		String str = constantProperties.SERVERPORT;
		System.out.println(str);
		return str;
	}

}
