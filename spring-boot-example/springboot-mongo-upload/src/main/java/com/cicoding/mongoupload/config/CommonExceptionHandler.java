package com.cicoding.mongoupload.config;

import com.cicoding.mongoupload.entity.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局统一异常处理
 * 捕获异常，产生异常时，统一返回错误信息
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel handle(Exception e) {
        ResponseModel model = ResponseModel.getInstance();
        if (e instanceof MaxUploadSizeExceededException) {
            model.setMessage("上传的文件超过大小限制");
        } else {
            model.setMessage("上传失败");
        }
        return model;
    }

}


