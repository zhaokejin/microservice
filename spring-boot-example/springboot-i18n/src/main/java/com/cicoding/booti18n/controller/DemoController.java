package com.cicoding.booti18n.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @DESC
 * @Author: cicoding.cn
 * @Date:2020-2-3 08:55:49
 * @Time:下午4:34
 */
@Controller
public class DemoController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/mess")
    public String mess(HttpServletRequest request, HttpServletResponse response, String lang){
        SessionLocaleResolver slr = new SessionLocaleResolver();
        String[] split = lang.split("_");
        Locale locale = new Locale(split[0], split[1]);
        slr.setLocale(request, response, locale);
        return "index";
    }
}