package cn.cicoding.user.feign;

import cn.cicoding.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "spring-cloud-alibaba-provider")
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Integer id);
}
