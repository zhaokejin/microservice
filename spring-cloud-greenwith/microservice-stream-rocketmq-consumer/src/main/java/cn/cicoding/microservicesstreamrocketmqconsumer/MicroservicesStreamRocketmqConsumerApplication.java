package cn.cicoding.microservicesstreamrocketmqconsumer;

import cn.cicoding.microservicesstreamrocketmqconsumer.sink.MySink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding({Sink.class, MySink.class})
@SpringBootApplication
public class MicroservicesStreamRocketmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesStreamRocketmqConsumerApplication.class, args);
	}

}
