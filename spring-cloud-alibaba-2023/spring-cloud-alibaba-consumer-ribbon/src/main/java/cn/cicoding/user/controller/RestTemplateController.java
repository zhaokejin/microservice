package cn.cicoding.user.controller;

import cn.cicoding.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/ribbon")
@RestController
public class RestTemplateController {

  @Autowired(required = false)
  private RestTemplate restTemplate;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    // 这里用到了RestTemplate的占位符能力
    User user = this.restTemplate.getForObject("http://spring-cloud-alibaba-provider/user/{id}", User.class, id);

    // 也可以使用ip:port的方式（不建议）
//    User user = this.restTemplate.getForObject("http://localhost:8080/user/{id}", User.class, id);
    // ...微服务的业务...
    return user;
  }
}
