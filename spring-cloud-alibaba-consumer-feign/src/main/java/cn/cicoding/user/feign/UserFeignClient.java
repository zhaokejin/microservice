package cn.cicoding.user.feign;

import cn.cicoding.user.entity.User;
import cn.cicoding.user.feign.fallback.UserFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "spring-cloud-alibaba-provider",
        fallbackFactory = UserFeignClientFallbackFactory.class
)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Integer id);
}
