package com.abc.controller;

import com.abc.bean.Student;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 使用普通方式定义客户端处理器
@RestController
public class StudentController {

    // 创建webClient客户端，其参数baseUrl用于与下面处理器方法中的uri进行拼接
    // 向服务端提交请求
    private WebClient client = WebClient.create("http://localhost:8080/student");

    // 查询所有
    @GetMapping("/list")
    public Flux<Student> listHandler() {
        return client.get()   // 提交get请求
                .uri("/all")  // 提交请求的uri，其会自动与WebClient的baseUrl相拼接
                .retrieve()   // 提交请求
                .bodyToFlux(Student.class);   // 接收服务端发送来的响应体，并将其转换为Flux
    }

    // 添加
    @PostMapping("/add")
    public Flux<Student> addHandler(@RequestBody Student student) {
        return client.post()
                .uri("/save")
                .body(Mono.just(student), Student.class) // 为请求添加请求体
                .retrieve()
                .bodyToFlux(Student.class);
    }

    // 根据id删除
    @DeleteMapping("/remove/{id}")
    public Mono<Void> removeHandler(@PathVariable("id") String id) {
        return client.delete()
                .uri("/del/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    // 修改
    @PutMapping("/modify/{id}")
    public Flux<Student> modifyHandler(@PathVariable("id") String id, @RequestBody Student student) {
        return client.put()
                .uri("/update/{id}", id)
                .body(Mono.just(student), Student.class)
                .retrieve()
                .bodyToFlux(Student.class);
    }

    // 根据id查询
    @GetMapping("/get/{id}")
    public Mono<Student> getHandler(@PathVariable("id") String id) {
        Mono<Student> studentMono = client.get()
                .uri("/find/{id}", id)
                .retrieve()
                .bodyToMono(Student.class);
        studentMono.subscribe();
        return client.get()
                .uri("/find/{id}", id)
                .retrieve()
                .bodyToMono(Student.class);
    }

    // 根据年龄范围查询
    @GetMapping("/range/{below}/{top}")
    public Flux<Student>  rangeHandler(@PathVariable("below") int below, @PathVariable("top") int top) {
        return client.get()
                .uri("/age/{below}/{top}", below, top)
                .retrieve()
                .bodyToFlux(Student.class);
    }
}
