package cn.cicoding.config;

import cn.cicoding.intercepter.TokenBucketIntercepter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {

    public static final Logger logger = LoggerFactory.getLogger(MyWebConfig.class);

    @Autowired
    private TokenBucketIntercepter tokenBucketIntercepter;

    //注册拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        System.out.println("===>注册拦截器");
        registry.addInterceptor(tokenBucketIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**");
        super.addInterceptors(registry);
    }

}