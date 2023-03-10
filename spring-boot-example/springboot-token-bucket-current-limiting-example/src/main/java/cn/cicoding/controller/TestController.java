package cn.cicoding.controller;

import cn.cicoding.annotation.LimitingRequired;
import cn.cicoding.config.Constant;
import cn.cicoding.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "test")
    @LimitingRequired(required = true)
    public ResponseEntity test() {
        ListOperations listOperations = redisTemplate.opsForList();
        return new ResponseEntity<>("test:队列长度:" + listOperations.size(Constant.TOKEN_BUCKET_KEY));
    }
}
