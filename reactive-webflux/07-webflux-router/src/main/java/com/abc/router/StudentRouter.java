package com.abc.router;

import com.abc.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration  // JavaConfig类
public class StudentRouter {

    // 用于将请求路由到指定的处理器方法

    @Bean
    RouterFunction<ServerResponse> customRouter(StudentHandler handler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/student"),   // 相当于@RequestMapping("/student")
                      RouterFunctions.route(RequestPredicates.GET("/all"), handler::findAllHandler)
                        .andRoute(RequestPredicates.POST("/save")
                                          .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::saveHandler)
                        .andRoute(RequestPredicates.DELETE("/del/{id}"), handler::delHandler)
                        .andRoute(RequestPredicates.PUT("/update/{id}")
                                          .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::updateHandler)
                        .andRoute(RequestPredicates.GET("/find/{id}"), handler::findByIdHandler)
                        .andRoute(RequestPredicates.GET("/age/{below}/{top}"), handler::findByAgeHandler)
                        .andRoute(RequestPredicates.GET("/sse/age/{below}/{top}"), handler::findByAgeSseHandler)
                );
    }
}
