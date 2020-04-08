package com.cicoding.config.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantProperties {

	@Value("${server.port}")
	public String SERVERPORT;

}
