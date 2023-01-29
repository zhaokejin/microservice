package com.cicoding.config.apollo;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhaokejin
 * @description
 * @date 2020/4/8
 */
@Component
public class ConfigRefresh {

	@Resource
	private DevConfig devConfig;

	@Resource
	private RefreshScope refreshScope;

	@ApolloConfigChangeListener({"dev"})
	public  void onChange(ConfigChangeEvent changeEvent) {
		boolean configChange = false;
		for (String  changeKey : changeEvent.changedKeys()) {
			if(changeKey.startsWith("apollo.dev")){
				configChange = true;
				break;
			}
		}
		if (!configChange){
			return;
		}
		refreshScope.refresh("devConfig");
	}
}
