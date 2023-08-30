package com.abc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j  //lombok日志记录
@RestController
public class SomeConstroller {

    // 定义耗时操作
    private String doSome(String name) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    @RequestMapping("/common")
    public String commonHandle() {
        log.info("common--start");
        String result = doSome("common-handler");
        log.info("common--end");
        return result;
    }

    @RequestMapping("/mono")
    public Mono<String> monoHandle() {
        log.info("mono--start");
        Mono<String> mono = Mono.fromSupplier(() -> doSome("mono-handler"));
        log.info("mono--end");
        return mono;
    }

    @RequestMapping("/flux")
    public Flux<String> fluxHandle() {
        // Mono：表示包含0-1个元素的异步序列
        // Flux：表示包含0-n个元素的异步序列
        return Flux.just("Beijing", "Shanghai", "Guangzhou");
    }

    @RequestMapping("/array")
    public Flux<String> arrayHandle(@RequestParam String[] interests) {
        // 将数组转为Flux
        return Flux.fromArray(interests);
    }

    @RequestMapping("/list")
    public Flux<String> arrayHandle(@RequestParam List<String> interests) {
        // 将集合转为Flux
        return Flux.fromStream(interests.stream());
    }

    @RequestMapping("/time")
    public Flux<String> timeHandle(@RequestParam List<String> interests) {
        log.info("flux -- start");
        Flux<String> flux = Flux.fromStream(interests.stream().map(i -> doSome("elem-" + i)));
        log.info("flux -- end");
        return flux;
    }

    // SSE，Server-Sent Event，服务端推送事件
    @RequestMapping(value = "/sse", produces = "text/event-stream")
    public Flux<String> sseHandle() {
        return Flux.just("Beijing", "Shanghai", "Guangzhou");
    }

}
