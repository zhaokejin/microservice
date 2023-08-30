package com.example.springbootdatasource.controller;

import com.example.springbootdatasource.model.UserDomain;
import com.example.springbootdatasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity addUser() {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserName("userName");
        userDomain.setPassword("password");
        userDomain.setPhone("phone");
        userService.insert(userDomain);
        return ResponseEntity.ok("添加成功");
    }

}
