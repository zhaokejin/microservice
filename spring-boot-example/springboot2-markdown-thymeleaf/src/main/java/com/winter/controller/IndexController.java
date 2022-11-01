package com.winter.controller;

import com.winter.model.Blog;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/***
 * index controller
 * 
 * @author zhaokejin
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
	@RequestMapping("/index")
	public String indexPage() {
		return "index";
	}


	@RequestMapping("submit")
	@ResponseBody
	public void  submit(Blog blog){
		System.out.println(blog.getContent());
		System.out.println(blog.getHtmlContent());
		userService.addBlog(blog);
	}

	@RequestMapping("view/{id}")
	public  String view(Model model,@PathVariable int id){
		Blog blog =   userService.findBlogById(id);
		System.out.println(blog.getHtmlContent());
		model.addAttribute("blog",blog);
		return  "view";
	}
}
