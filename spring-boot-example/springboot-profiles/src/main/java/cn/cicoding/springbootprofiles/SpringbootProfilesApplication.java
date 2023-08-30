package cn.cicoding.springbootprofiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参考链接：
 * https://www.cnblogs.com/ruanjianlaowang/p/11182698.html
 * https://blog.csdn.net/qq_31289187/article/details/85116371
 */
@RestController
@SpringBootApplication
public class SpringbootProfilesApplication {

	@Value("${profiles.str}")
	private String str;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProfilesApplication.class, args);
	}

	@GetMapping("test")
	public String test(){
		return str;
	}
}
