package com.abc.handler;

import com.abc.bean.Student;
import com.abc.repository.StudentRepository;
import com.abc.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

// 处理器类
@Component
public class StudentHandler {

    @Autowired
    private StudentRepository repository;

    // 查询所有
    public Mono<ServerResponse> findAllHandler(ServerRequest request) {
        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(repository.findAll(), Student.class);
    }

    // 添加数据
    public Mono<ServerResponse> saveHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        Mono<Student> studentMono = request.bodyToMono(Student.class);

        studentMono = studentMono.map(stu -> {
            // 验证Student对象
            ValidatorUtil.validateStudent(stu);
            return stu;
        });

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(repository.saveAll(studentMono), Student.class);
    }

    // 有状态删除
    public Mono<ServerResponse> delHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");

        return repository.findById(id)
                .flatMap(stu -> repository.deleteById(id)
                                          .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // 有状态修改
    public Mono<ServerResponse> updateHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");
        Mono<Student> studentMono = request.bodyToMono(Student.class);

        // 将id封装到了Mono的元素中
        Mono<Student> studentMonoId = studentMono.map(stu -> {
            // 验证Student对象
            ValidatorUtil.validateStudent(stu);
            stu.setId(id);
            return stu;
        });

        return repository.findById(id)
                .flatMap(stu -> ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(repository.saveAll(studentMonoId), Student.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // 有状态查询
    public Mono<ServerResponse> findByIdHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String id = request.pathVariable("id");

        return repository.findById(id)
                .flatMap(stu -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(repository.findById(id), Student.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    // 根据年龄范围查询(一次性返回数据)
    public Mono<ServerResponse> findByAgeHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String belowStr = request.pathVariable("below");
        String topStr = request.pathVariable("top");
        Integer below = Integer.valueOf(belowStr);
        Integer top = Integer.valueOf(topStr);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(repository.findByAgeBetween(below, top), Student.class);
    }

    // 根据年龄范围查询(以SSE形式返回数据)
    public Mono<ServerResponse> findByAgeSseHandler(ServerRequest request) {
        // 从请求中获取到要添加的数据
        String belowStr = request.pathVariable("below");
        String topStr = request.pathVariable("top");
        Integer below = Integer.valueOf(belowStr);
        Integer top = Integer.valueOf(topStr);

        return ServerResponse
                .ok()   // 响应码200
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.findByAgeBetween(below, top), Student.class);
    }
}
