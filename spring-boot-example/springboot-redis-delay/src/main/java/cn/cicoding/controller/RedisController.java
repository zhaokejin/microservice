package cn.cicoding.controller;

import cn.cicoding.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/redis")
@RestController
public class RedisController {


    @Resource
    RedisUtils redisUtils;

    /**
     * 发布消息
     *
     * @param id
     * @return
     */
    @GetMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable String id) {
        int dbIndex = 14;
        String key = "TIME_KEY:ttttt";
        redisUtils.set(key, "test", dbIndex, 20);
        System.out.println(redisUtils.get(key, dbIndex));
        redisUtils.set(key + "1", key, 4, 30);
        return "success";
    }

}