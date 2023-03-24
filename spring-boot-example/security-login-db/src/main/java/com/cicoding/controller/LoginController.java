package com.cicoding.controller;

import com.cicoding.controller.base.BaseController;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户登录的请求类
 * @author weicong
 * @version 1.0 createTimes 2018-12-15 11:10:00
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

//    @Resource
//    private UserService userService;

//    @PostMapping("/login")
//    @ResponseBody
//    public Map<String, Object> login(String loginName, String password, HttpSession session){
//        return userService.login(loginName, password, session);
//    }

    @RequestMapping
    public String loginPage(HttpSession httpSession, String username, Model model) {
        // 获取认证失败的exception
        AuthenticationException exception = (AuthenticationException) httpSession
                .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        if (exception != null) {
            model.addAttribute("loginError", exception.getMessage());
            httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
        model.addAttribute("username", username);
        return "login";
    }

}
