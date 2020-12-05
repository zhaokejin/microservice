package cn.cicoding.cloud.config;

import cn.cicoding.cloud.user.entity.ErrorInfo;
import cn.cicoding.cloud.user.entity.ResultSet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestControllerAdvice
//@ConditionalOnClass(name="feign.FeignException")
public class FeignControllerExceptionHandler {
	
	static Logger logger = LoggerFactory.getLogger(FeignControllerExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    public ResultSet defaultErrorHandler(HttpServletRequest req, HttpServletResponse resp, BaseException e) {
	    ResultSet resultSet = new ResultSet();

		if (e.getStatus() == 400) {
			resultSet.setResultCode(-1);
			resultSet.setResultMsg(e.getMessage());
			resultSet.setData(null);
			resp.setStatus(400);
		} else {
			resp.setStatus(500);
			if(logger.isErrorEnabled()){
				logger.error("系统异常，请联系系统开发人员进行处理", e);
			}
			resultSet.setResultCode(-1);
			resultSet.setResultMsg(e.getMessage());
			resultSet.setData(null);
		}

	    return resultSet;
    } 
}
