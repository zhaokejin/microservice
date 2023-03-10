package cn.cicoding.intercepter;
 
import cn.cicoding.annotation.LimitingRequired;
import cn.cicoding.config.Constant;
import cn.cicoding.entity.ResponseEntity;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
 
@Component
public class TokenBucketIntercepter extends HandlerInterceptorAdapter {
 
    private static final Logger logger = LoggerFactory.getLogger(TokenBucketIntercepter.class);
 
    @Autowired
    private RedisTemplate redisTemplate;
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        LimitingRequired annotation = method.getAnnotation(LimitingRequired.class);
        //判断是否需要限流
        if (annotation != null && annotation.required()) {
            boolean isAllowAccess = isAllowAccess();
            if (!isAllowAccess) {
                returnJson(response, "当前访问人数过多，请稍后重试！");
                return false;
            }
        }
        return true;
    }
 
    private boolean isAllowAccess() {
        ListOperations listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(Constant.TOKEN_BUCKET_KEY);
        //令牌桶中没有令牌，返回false，这样就起到了限流的作用
        logger.info("令牌数量：" + size);
        if (size < 1) {
            logger.error("请求-----------502");
            return false;
        }
        //认定当前请求有效，取出令牌桶
        listOperations.rightPop(Constant.TOKEN_BUCKET_KEY);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //logger.info("postHandle");
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion-访问路径:" + request.getContextPath() + request.getServletPath());
    }
 
    private void returnJson(HttpServletResponse response, String msg) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        try {
            writer = response.getWriter();
            ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.BAD_GATEWAY.value(), msg, msg);
            Object o = JSONObject.toJSON(responseEntity);
            writer.print(o);
            writer.flush();
        } catch (IOException e) {
            logger.error("拦截器输出流异常" + e.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}