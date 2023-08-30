package cn.cicoding;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping
	public Object test () {
		return Collections.singletonMap("success", "true");
	}
}