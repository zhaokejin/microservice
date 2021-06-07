package cn.cicoding.microservicesstreamrocketmqproducer.rocketmq;

import cn.cicoding.microservicesstreamrocketmqproducer.source.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者 生产消息
 * @author zhaokejin
 * @description
 * @date 2019/12/28
 */

@RestController
public class ProducerController {

	@Autowired
	private Source source;
	@Autowired
	private MySource mySource;

	@GetMapping("/test-stream")
	public String testStream(){
		this.source.output().send(
				MessageBuilder.withPayload("output RocketMQ消息！！").build()
		);

		return "success";
	}

	@GetMapping("/myoutput")
	public String output1(){
		this.mySource.output1().send(
				MessageBuilder.withPayload("output1 RocketMQ消息！！").build()
		);

		return "success";
	}
}
