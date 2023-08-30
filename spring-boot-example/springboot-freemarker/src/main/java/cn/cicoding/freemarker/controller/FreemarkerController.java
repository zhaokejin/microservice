package cn.cicoding.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created with IntelliJ IDEA.
 * author: cn.cicoding
 */
@Controller
public class FreemarkerController {


    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("content", "Freemarker");
        return "index";
    }

}
