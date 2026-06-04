package cn.cicoding.consumer.controller;

import cn.cicoding.demo.serivce.SayService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neo.Zzj
 * @date 2020/12/18
 */
@RestController
@RequestMapping("/demo/say")
public class SayController {

    @DubboReference
    private SayService sayService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(@RequestParam("name") String name) {
        return ResponseEntity.ok(sayService.sayHelloByName(name));
    }
}
