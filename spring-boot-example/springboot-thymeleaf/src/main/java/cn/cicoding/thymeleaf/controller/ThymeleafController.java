package cn.cicoding.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 */
@Controller
public class ThymeleafController {


    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("content", "Hello Thymeleaf");
        return "index";
    }


}
