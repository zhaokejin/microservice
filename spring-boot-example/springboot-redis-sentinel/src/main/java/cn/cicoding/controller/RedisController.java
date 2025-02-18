package cn.cicoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    StringRedisTemplate template;

    /**
     * 发布消息
     *
     * @param id
     * @return
     */
    @GetMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable String id) {
        for (int i = 1; i <= 5; i++) {
            template.convertAndSend("channel:test", String.format("我是消息{%d}号: %tT", i, new Date()));
        }
        return "success";
    }

}