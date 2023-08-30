package com.cicoding.controller.base;

import com.cicoding.vo.rep.base.RepBaseVO;
import com.cicoding.vo.rep.base.ResultCode;
import com.cicoding.exception.ApplicationRuntimeException;
import com.cicoding.utils.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public class ApiBaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApiBaseController.class);
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RepBaseVO resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
									  Exception ex) {
		LOG.error("应用异常" + DateUtil.getTime(), ex);
		// response.setContentType("application/json;charset=UTF-8");
		RepBaseVO<String> repVO = new RepBaseVO<>();
		if (ex instanceof ApplicationRuntimeException) {
			repVO.setCode(ResultCode.COMMON_FAIL).setMes(ex.getMessage());
		} else {
			repVO.setCode(ResultCode.COMMON_FAIL).setMes("系统繁忙");
		}
		// try {
		// response.getWriter().print(JSON.toJSONString(repVO));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return repVO;
		
	}
	
}
