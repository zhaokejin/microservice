package cn.cicoding.controller;

import cn.cicoding.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/ribbon")
@RestController
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        // 通过服务名调用，LoadBalancer 自动负载均衡
        return restTemplate.getForObject(
                "http://spring-cloud-alibaba-provider/user/{id}", User.class, id);
    }
}
