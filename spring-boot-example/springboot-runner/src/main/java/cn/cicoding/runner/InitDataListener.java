package cn.cicoding.runner;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;


@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("系统启动中。。。002");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("系统启动中。。。003");
    }
}
