package com.example.config;

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
 * @Author changyandong@e6yun.com
 * @Created Date: 2018/7/11 14:20
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
        String jwtHeader = "bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwaG9uZSI6bnVsbCwidXNlcl9uYW1lIjoiemhhb2tqIiwic2NvcGUiOlsiYWxsIl0sImlzQWRtaW4iOmZhbHNlLCJleHAiOjE1Njc5OTEyODUsInVzZXJOYW1lIjoiemhhb2tqIiwidXNlcklkIjoiIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjRjNDdhYTY0LThmMDctNDg2My04MmIzLTYxYmQ1MzJlZjNmMyIsInVzZXJDb2RlIjoiemhhb2tqIiwiZW1haWwiOiJudWxsIiwiY2xpZW50X2lkIjoiMTAwMDAwMDEwMiJ9.hkmIvgg4ivpyyMiLODx5hGtfDYu8SQTscVmll6ce9nAoOWCCE0AXmxSC0aPOIMBlCUBMqmYK6xLi31SBJVoC1ym0pxDKZ1nsJw3efQKsac3bfAd7gmWhiQw1LbzHKUksmTwCa6oVYS9877eTwpfWM9QUBz-IxJYo-26VTr4Cg9rWm6LVyRpTd6xU_wJGBbAei-af-K1axcRZ1uGmYPGw7P6wG1Ts03Gph65_7Z5zEBXiijxlp789qyN6QsrZBPOgnC0ncCaacMxMLsn1pk3vqQP5NNbOg6UxXavmsRg4579qnhl5Ug-jTWXo4Ttj4dCGzBSrMfrecc_heJOo_RZH4g";
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