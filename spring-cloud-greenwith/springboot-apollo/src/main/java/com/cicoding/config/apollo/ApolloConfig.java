package com.cicoding.config.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaokejin
 * @description
 * @date 2020/4/8
 */
@Configuration
@EnableApolloConfig({"dev"})
public class ApolloConfig {

	@Value("${apollo.dev.str1}")
	private String str;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
