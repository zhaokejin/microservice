package com.example.user.controller;

import com.example.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

  @RequestMapping("/ribbon")
@RestController
public class RibbonController {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    // 这里用到了RestTemplate的占位符能力
    User user = this.restTemplate.getForObject("http://microservice-provider/user/{id}", User.class, id);
//    User user = this.restTemplate.getForObject("http://microservice-provider-user/users/{id}", User.class, id);
//    User user = this.restTemplate.getForObject("http://10.254.193.129:8180/user/{id}", User.class, id);
    return user;
  }
}
