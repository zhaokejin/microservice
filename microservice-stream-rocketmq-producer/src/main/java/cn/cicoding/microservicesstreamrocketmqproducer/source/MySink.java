package cn.cicoding.microservicesstreamrocketmqproducer.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhaokejin
 * @description
 * @date 2020/4/15
 */
public interface MySink {

	//接收队列1
	String MYINPUT = "input1";

	@Input
	MessageChannel input1();
}
