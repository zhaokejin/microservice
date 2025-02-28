package com.example.microservicehystrixdashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HystrixIndexController {
	@GetMapping("")
	public String index() {
		return "forward:/hystrix";
	}
}