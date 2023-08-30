package cn.cicoding.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "Hello World!";
    }

}