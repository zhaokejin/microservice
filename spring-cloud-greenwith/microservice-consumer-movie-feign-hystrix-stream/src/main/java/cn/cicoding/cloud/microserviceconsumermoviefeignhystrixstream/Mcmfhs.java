package cn.cicoding.cloud.microserviceconsumermoviefeignhystrixstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableCircuitBreaker
public class Mcmfhs {

	public static void main(String[] args) {
		SpringApplication.run(Mcmfhs.class, args);
	}

}

