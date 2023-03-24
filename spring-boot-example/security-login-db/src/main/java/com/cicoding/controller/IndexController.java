package com.cicoding.controller;

import com.cicoding.controller.base.BaseController;
import com.cicoding.model.User;
import com.cicoding.service.UserService;
import com.cicoding.vo.rep.base.RepBaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈首页〉
 *
 * @author zhaokejin
 * @create 2019/1/29
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@Autowired
	private UserService userService;

	/***
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping("/")
	public String indexPage() {
		return "index";
	}

	@GetMapping("register")
	public String registerPage() {
		return "register";
	}

	@PostMapping("register")
	@ResponseBody
	public RepBaseVO register(String username, String password, String email) {
		RepBaseVO repVO = new RepBaseVO<>();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddDate(new Date());
		userService.register(user);
		return repVO.setCommonSuccess();
	}

}