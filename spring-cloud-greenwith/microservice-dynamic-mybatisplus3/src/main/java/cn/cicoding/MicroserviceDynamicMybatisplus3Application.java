package cn.cicoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("cn.cicoding.mapper")
public class MicroserviceDynamicMybatisplus3Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDynamicMybatisplus3Application.class, args);
	}

}
