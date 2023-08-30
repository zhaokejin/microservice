package com.abc.handler;

import com.abc.bean.Student;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// 处理器类
@Component
public class StudentHandler {

    private WebClient client = WebClient.create("http://localhost:8080/student");

    // 查询所有
    public Mono<ServerResponse> findAllHandler(ServerRequest request) {
        Flux<Student> studentFlux = client.get()   // 提交get请求
                .uri("/all")  // 提交请求的uri，其会自动与WebClient的baseUrl相拼接
                .retrieve()   // 提交请求
                .bodyToFlux(Student.class);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(studentFlux, Student.class);
    }

    // 添加数据
    public Mono<ServerResponse> saveHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        Mono<Student> studentMono = request.bodyToMono(Student.class);

        Flux<Student> studentFlux = client.post()
                .uri("/save")
                .body(studentMono, Student.class) // 为请求添加请求体
                .retrieve()
                .bodyToFlux(Student.class);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(studentFlux, Student.class);
    }

    // 有状态删除
    public Mono<ServerResponse> delHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");

        Mono<Void> voidMono = client.delete()
                .uri("/del/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);

        return ServerResponse
                .ok()   // 响应码200
                .body(voidMono, Void.class);
    }

    // 有状态修改
    public Mono<ServerResponse> updateHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");
        Mono<Student> studentMono = request.bodyToMono(Student.class);

        // 将id封装到了Mono的元素中
        Mono<Student> studentMonoId = studentMono.map(stu -> {
            stu.setId(id);
            return stu;
        });

        Flux<Student> studentFlux = client.put()
                .uri("/update/{id}", id)
                .body(studentMonoId, Student.class)
                .retrieve()
                .bodyToFlux(Student.class);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(studentFlux, Student.class);
    }

    // 有状态查询
    public Mono<ServerResponse> findByIdHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");

        Mono<Student> studentMono = client.get()
                .uri("/find/{id}", id)
                .retrieve()
                .bodyToMono(Student.class);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(studentMono, Student.class);
    }

    // 根据年龄范围查询(一次性返回数据)
    public Mono<ServerResponse> findByAgeHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String belowStr = request.pathVariable("below");
        String topStr = request.pathVariable("top");
        Integer below = Integer.valueOf(belowStr);
        Integer top = Integer.valueOf(topStr);

        Flux<Student> studentFlux = client.get()
                .uri("/age/{below}/{top}", below, top)
                .retrieve()
                .bodyToFlux(Student.class);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(studentFlux, Student.class);
    }

}
