package cn.cicoding.env.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 * description:
 */
@RestController
@RequestMapping
public class TestController {

    @Value("${test.env}")
    private String env;

    @RequestMapping("/test")
    public String testMultipartEnv(){
        return "当前启动环境为:[" + env + "]";
    }


}
