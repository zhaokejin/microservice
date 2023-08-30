package com.cicoding.controller;

import com.cicoding.model.UserDomain;
import com.cicoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/find", produces = {"application/json;charset=UTF-8"})
    public Object find(@RequestParam(name = "city", required = false) String city,@RequestParam(name = "level", required = false) String level) {
        List<UserDomain> allUserList = userService.findAllUserList();
        return allUserList;
    }


}