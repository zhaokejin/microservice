package com.example.cloud.user.controller;

import com.example.cloud.user.entity.User;
import com.example.cloud.user.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zkj
 */
@RequestMapping("/fegin")
@RestController
public class MovieController {
  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Integer id) {
    return this.userFeignClient.findById(id);
  }
}
