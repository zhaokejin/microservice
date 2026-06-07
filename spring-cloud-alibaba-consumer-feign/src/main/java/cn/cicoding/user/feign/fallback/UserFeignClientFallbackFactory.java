package cn.cicoding.user.feign.fallback;

import cn.cicoding.user.entity.User;
import cn.cicoding.user.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * UserFeignClient 熔断降级工厂
 * <p>
 * 当 Provider 服务不可用或调用超时时，返回降级结果
 */
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger log = LoggerFactory.getLogger(UserFeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        log.error("Feign 调用 Provider 降级: {}", cause.getMessage(), cause);
        return id -> {
            User fallback = new User();
            fallback.setId(id);
            fallback.setUserName("服务降级 - 暂不可用");
            fallback.setPassword("");
            fallback.setPhone("");
            return fallback;
        };
    }
}
