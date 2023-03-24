package com.cicoding.controller.base;

import com.cicoding.vo.rep.base.RepBaseVO;
import com.cicoding.vo.rep.base.ResultCode;
import com.cicoding.exception.ApplicationRuntimeException;
import com.cicoding.model.User;
import com.cicoding.utils.date.DateUtil;
import com.cicoding.utils.json.JsonUtils;
import com.cicoding.utils.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * controller base
 * @author zhaokejin
 *
 */
public class BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	
	/** 默认错误消息 */
	private static final String DEFAULT_ERROR_MESSAGE = "系统繁忙";
	
	/** 权限不足消息 */
	private static final String ACCESS_DENY_MESSAGE = "权限不足";
	
	/***
	 * 处理其他异常
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
										 Exception ex) {
		LOG.error("应用异常" + DateUtil.getTime(), ex);
		ModelAndView mv = new ModelAndView();
		String errorMessage = DEFAULT_ERROR_MESSAGE;
		ResultCode errorCode = ResultCode.COMMON_FAIL;
		String viewName = "error/5xx";
		// 如果是应用错误，返回异常中的信息
		if (ex instanceof ApplicationRuntimeException) {
			errorMessage = ex.getMessage();
		}
		// 如果是权限不足，返回权限不足信息
		if (ex instanceof AccessDeniedException) {
			errorMessage = ACCESS_DENY_MESSAGE;
			viewName = "error/401";
			errorCode = ResultCode.ACCESS_DENIED;
		}
		
		// 如果是ajax请求，则返回json
		if (AjaxUtils.isAjaxRequest(request) || AjaxUtils.isAjaxUploadRequest(request)) {
			response.setContentType("application/json;charset=UTF-8");
			RepBaseVO<String> repVO = new RepBaseVO<>();
			repVO.setCode(errorCode).setMes(errorMessage);
			try {
				response.getWriter().print(JsonUtils.beanToString(repVO));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
		// 其他请求，返回html页面
		mv.setViewName(viewName);
		mv.addObject("errorMessage", errorMessage);
		return mv;
	}
	
	@ModelAttribute(value = "user")
	public User myModel() {
		return getUser();
	}
	
	public User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principles = authentication.getPrincipal();
		if (principles == null) {
			return null;
		}
		if (!(principles instanceof User)) {
			return null;
		}
		return (User) principles;
	}
	
}
