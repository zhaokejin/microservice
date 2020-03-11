package cn.cicoding.controller;

import cn.cicoding.service.TestService;
import cn.cicoding.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class TestController {


    @Autowired
    private TestService userService;

    @GetMapping("/{userId}")
    public User findById(@PathVariable Integer userId) {
        return this.userService.findById(userId);
    }
}

