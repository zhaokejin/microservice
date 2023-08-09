package cn.cicoding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/runner")
@RestController
public class RunnerController {

    @GetMapping("/runner")
    public String runner(){
        return "success";
    }
}
