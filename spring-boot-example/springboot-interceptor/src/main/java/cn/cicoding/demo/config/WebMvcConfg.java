package cn.cicoding.demo.config;

import cn.cicoding.demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 */
@Configuration
public class WebMvcConfg extends WebMvcConfigurerAdapter {

    @Resource
    private MyInterceptor myInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(myInterceptor).addPathPatterns("api/**").excludePathPatterns("api/login");
    }

}