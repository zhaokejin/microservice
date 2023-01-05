package com.winter.controller;

import com.winter.model.UserDomain;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/***
 * index controller
 * 
 * @author zhaokj
 *
 */
@Controller
public class IndexController {
	@Autowired
	private UserService userService;
	/***
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String indexPage(HttpServletRequest request) {
		List<UserDomain> list = userService.findAllUserList();
		request.setAttribute("list", list);
		return "index";
	}

}
