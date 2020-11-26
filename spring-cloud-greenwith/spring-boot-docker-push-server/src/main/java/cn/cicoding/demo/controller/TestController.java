package cn.cicoding.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "test", produces = "text/plain;charset=utf-8")
    public String authGet(String str) {
        return "success";
    }

}
