package cn.cicoding.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description  全局过滤器 在这里可以实现记录日志和访问权限校验等
 * @Author zhaokejin
 * @Created Date: 2020/03/19
 * @ClassName AuthSignatureFilter
 * @Version: 1.0
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
 
    static Logger logger = LoggerFactory.getLogger(AuthSignatureFilter.class);
 
    /**
     * 全局过滤器 核心方法
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String jwtHeader = "bearer  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        String token = exchange.getRequest().getQueryParams().getFirst("authToken");
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate().header("Authorization", jwtHeader).build();
        logger.info("Authorization >>>>> 添加成功!");
        //将现在的request 变成 change对象 
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }
 
    @Override
    public int getOrder() {
        return -200;
    }
}