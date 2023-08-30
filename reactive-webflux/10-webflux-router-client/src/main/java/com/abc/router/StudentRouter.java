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

    @Bean
    RouterFunction<ServerResponse> customRouter(StudentHandler handler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/"),
                      RouterFunctions.route(RequestPredicates.GET("/list"), handler::findAllHandler)
                        .andRoute(RequestPredicates.POST("/add")
                                          .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::saveHandler)
                        .andRoute(RequestPredicates.DELETE("/remove/{id}"), handler::delHandler)
                        .andRoute(RequestPredicates.PUT("/modify/{id}")
                                          .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::updateHandler)
                        .andRoute(RequestPredicates.GET("/get/{id}"), handler::findByIdHandler)
                        .andRoute(RequestPredicates.GET("/range/{below}/{top}"), handler::findByAgeHandler)
                );
    }
}
