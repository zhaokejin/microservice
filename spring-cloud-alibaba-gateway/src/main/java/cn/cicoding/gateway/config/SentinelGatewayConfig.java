package cn.cicoding.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Sentinel 网关限流配置
 * <p>
 * 自定义限流/熔断后的响应格式，替代默认的 Blocked by Sentinel 错误页面
 */
@Configuration
public class SentinelGatewayConfig {

    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = (ServerWebExchange exchange, Throwable t) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("code", HttpStatus.TOO_MANY_REQUESTS.value());
            result.put("msg", "请求过于频繁，请稍后再试");
            result.put("data", null);
            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(result);
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
}
