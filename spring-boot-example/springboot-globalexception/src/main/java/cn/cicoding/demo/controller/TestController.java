package cn.cicoding.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: cicoding
 */
@RestController
public class TestController {

    @GetMapping(value="/abc")
    public Object testException(){
        int i = 5/0;
        return i;
    }


}