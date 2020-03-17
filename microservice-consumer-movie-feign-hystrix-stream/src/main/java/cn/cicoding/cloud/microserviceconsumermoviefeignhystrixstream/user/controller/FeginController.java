package cn.cicoding.cloud.microserviceconsumermoviefeignhystrixstream.user.controller;

import cn.cicoding.cloud.microserviceconsumermoviefeignhystrixstream.user.feign.UserFeignClient;
import cn.cicoding.cloud.microserviceconsumermoviefeignhystrixstream.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fegin")
@RestController
public class FeginController {
  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Integer id) {
    return this.userFeignClient.findById(id);
  }
}
