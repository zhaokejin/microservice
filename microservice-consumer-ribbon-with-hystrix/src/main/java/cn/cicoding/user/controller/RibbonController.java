package cn.cicoding.user.controller;

import cn.cicoding.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/ribbon")
@RestController
@Slf4j
public class RibbonController {
  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "findByIdFallback")
  @GetMapping("/user/{id}")
  public User findById(@PathVariable Integer id) {
    // 这里用到了RestTemplate的占位符能力
    User user = this.restTemplate.getForObject("http://microservice-provider/user/{id}", User.class, id);
    // ...电影微服务的业务...
    return user;
  }

  public User findByIdFallback(Integer id, Throwable throwable) {
    log.error("进入回退方法", throwable);
    return new User(id, "默认用户", "aaaaaa", "13906414017");
  }
}
