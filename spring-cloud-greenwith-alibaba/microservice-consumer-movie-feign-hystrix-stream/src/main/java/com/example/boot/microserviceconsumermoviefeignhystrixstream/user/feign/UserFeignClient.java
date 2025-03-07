package com.example.boot.microserviceconsumermoviefeignhystrixstream.user.feign;

import com.example.boot.microserviceconsumermoviefeignhystrixstream.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-provider", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
  @GetMapping("/user/{id}")
  User findById(@PathVariable("id") Integer id);
}

@Component
class UserFeignClientFallback implements UserFeignClient {
  @Override
  public User findById(Integer id) {
    return new User(id, "默认用户", "默认用户", "13987878754");
  }
}