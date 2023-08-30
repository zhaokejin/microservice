package com.abc.exception;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

// 异常处理器（当异常发生时，会捕获到异常，并可以对异常信息进行处理）
@Component
@Order(-99)   // 为当前组件设置执行优先级
public class StudentExceptionHandler implements WebExceptionHandler {

    // ServerWebExchange：可以拦截请求与响应，并提供对请求与响应的访问
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // 获取到响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 设置响应码
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        // 指定响应类型为普通文本
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        // 获取到异常信息
        String message = formatExceptionMessage(ex);
        DataBuffer dataBuffer = response.bufferFactory().wrap(message.getBytes());
        // 指定响应体为异常信息
        return response.writeWith(Mono.just(dataBuffer));
    }

    // 格式化异常信息
    private String formatExceptionMessage(Throwable ex) {
        String msg = ex.getMessage();
        if(ex instanceof StudentException) {
            StudentException e = (StudentException) ex;
            msg = msg + "【" + e.getErrorField() + "，" + e.getErrorValue() + "】";
        }
        return msg;
    }
}
