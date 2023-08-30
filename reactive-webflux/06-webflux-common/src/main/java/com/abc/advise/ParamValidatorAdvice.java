package com.abc.advise;

import com.abc.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

// 处理器切面，表明当前切面的连接点为处理器方法
@ControllerAdvice
public class ParamValidatorAdvice {

    // 定义异常处理器
    @ExceptionHandler
    public ResponseEntity<String> validateHandler(StudentException ex) {
        String msg = ex.getErrorField() + "【" + ex.getErrorValue() + ":" + ex.getMessage() + "】";
        return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
    }

    // 定义异常处理器
    @ExceptionHandler
    public ResponseEntity<String> validateHandler(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exToStr(ex), HttpStatus.BAD_REQUEST);
    }

    // 将异常对象WebExchangeBindException转换为异常信息String
    private String exToStr(WebExchangeBindException ex) {
        return ex.getFieldErrors()    // 集合，元素是出现异常的属性异常信息
                .stream()   // stream，元素为出现异常的属性异常信息
                .map(e -> e.getField() +":" + e.getDefaultMessage())  // 将Stream中的异常类型元素映射为了String类型元素
                .reduce("", (s1, s2) -> s1 + "\n" + s2);  // 将Stream中的String元素拼接为一个String
    }

}
