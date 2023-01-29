package com.cicoding.web.controller;

import com.cicoding.config.apollo.ApolloConfig;
import com.cicoding.config.apollo.ConstantProperties;
import com.cicoding.config.apollo.DevConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaokejin
 * @description
 * @date 2020/4/8
 */
@RestController
public class TestController {

	@Resource
	private ApolloConfig apolloConfig;

	@Resource
	private DevConfig devConfig;

	@RequestMapping("/test")
	public String test(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("apollo.dev.str1: ").append(devConfig.getStr1()).append("<br/>");
		stringBuilder.append("apollo.dev.str2: ").append(devConfig.getStr2()).append("<br/>");
		stringBuilder.append("apollo.dev.str3: ").append(devConfig.getStr3()).append("<br/>");
		stringBuilder.append("@Value注解，str1: ").append(apolloConfig.getStr()).append("<br/>");
		return  stringBuilder.toString();
	}

	@Autowired
	private ConstantProperties constantProperties;

	@RequestMapping("/dc")
	public String serverPort(){
		String str = constantProperties.SERVERPORT;
		System.out.println(str);
		return str;
	}
}
