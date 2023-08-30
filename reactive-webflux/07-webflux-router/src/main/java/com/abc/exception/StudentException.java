package com.abc.exception;

import lombok.Data;

// 自定义异常类
@Data
public class StudentException extends RuntimeException {
    private String errorField;
    private Object errorValue;

    public StudentException() {
        super();
    }

    public StudentException(String message, String errorField, Object errorValue) {
        super(message);
        this.errorField = errorField;
        this.errorValue = errorValue;
    }
}
