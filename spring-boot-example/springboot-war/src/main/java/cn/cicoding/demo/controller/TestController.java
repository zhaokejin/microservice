package cn.cicoding.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 * date: 2020/03/19
 * time: 12:34
 * description:
 */
@RestController
public class TestController {


    private final Map<String, Object> params = new HashMap<String, Object>();

    /**
     * 功能描述: 测试restful 协议， 从路径中获取字段
     * @param city_id
     * @param user_id
     * @return
     */
    @GetMapping("/{city_id}/{user_id}")
    public Object findObject(@PathVariable("city_id") String city_id, @PathVariable("user_id") String user_id){
        params.clear();
        params.put("city_id", city_id);
        params.put("user_id", user_id);
        return params;
    }


}