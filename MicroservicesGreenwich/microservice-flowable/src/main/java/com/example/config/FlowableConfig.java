package com.example.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author zhaokejin
 * @create 2019-5-13 11:35:51
 */
@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {


	@Override
	public void configure(SpringProcessEngineConfiguration engineConfiguration) {
		engineConfiguration.setActivityFontName("宋体");
		engineConfiguration.setLabelFontName("宋体");
		engineConfiguration.setAnnotationFontName("宋体");
	}
}