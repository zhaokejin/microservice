package com.cicoding.config.apollo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author zhaokejin
 * @description
 * @date 2020/4/8
 */
@Component
@ConfigurationProperties(prefix = "apollo.dev")
@RefreshScope
public class DevConfig {

	private String str1;
	private String str2;
	private String str3;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}
}

