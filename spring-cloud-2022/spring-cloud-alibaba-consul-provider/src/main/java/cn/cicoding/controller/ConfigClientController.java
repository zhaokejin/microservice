//package cn.cicoding.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by macro on 2023/9/1.
// */
//@RestController
//@RefreshScope
//public class ConfigClientController {
//
//    @Value("${config.info}")
//    private String configInfo;
//
//    @GetMapping("/configInfo")
//    public String getConfigInfo() {
//        return configInfo;
//    }
//}
