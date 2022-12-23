package com.cicoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/index", "/home"})
    public String root(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("1231");
        return "login";
    }

    @PostMapping("/login")
    public String postlogin(){
        System.out.println("1231");
        return "login";
    }

}
