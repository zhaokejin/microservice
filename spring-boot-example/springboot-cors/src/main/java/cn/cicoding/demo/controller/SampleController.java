package cn.cicoding.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 * date: 2020/03/18
 * time: 23:17
 * description:
 */
@Controller
public class SampleController {

    @RequestMapping("/hello")
    @CrossOrigin("http://localhost:8080")
    public String hello( ){
        return "Hello World";
    }


}
