package com.abc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SomeConstroller {

    @RequestMapping("/common")
    public String commonHandle() {
        return "common handler";
    }

    @RequestMapping("/mono")
    public Mono<String> monoHandle() {
        // Mono表示一个包含0个或1个元素的异步序列
        return Mono.just("mono handler");
    }

}
