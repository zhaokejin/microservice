package cn.cicoding.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        if(requestURI.contains("my")){
            System.out.println("成功啦。。。, 请求URI是:" + requestURI);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}