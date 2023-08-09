package cn.cicoding;

import cn.cicoding.bean.StartupRunner;
import cn.cicoding.bean.TaskRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRunnerApplication.class, args);
    }

    @Bean
    public StartupRunner startupRunner(){
        return new StartupRunner();
    }

    @Bean
    public TaskRunner taskRunner(){
        return new TaskRunner();
    }

}
