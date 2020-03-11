package cn.cicoding.microservicesstreamrocketmqproducer.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaokejin
 * @description
 * @date 2019/12/28
 */

@RestController
public class ProducerController {

	@Autowired
	private Source source;

	@GetMapping("/test-stream")
	public String testStream(){
		this.source.output().send(
				MessageBuilder.withPayload("RocketMQ消息！！").build()
		);

		return "success";
	}
}
