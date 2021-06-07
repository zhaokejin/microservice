package cn.cicoding.microservicesstreamrocketmqconsumer.consumer;

import cn.cicoding.microservicesstreamrocketmqconsumer.sink.MySink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author zhaokejin
 * @description
 * @date 2019/11/15
 */
@Service
@Slf4j
public class TestStreamConsumer {

	@StreamListener(Sink.INPUT)
	public void receive(String messageBody){
		log.info("通过INPUT收到消息，messageBody = {}", messageBody);
	}

	@StreamListener(MySink.MYINPUT)
	public void myinput(String messageBody){
		log.info("通过MYINPUT收到消息，messageBody = {}", messageBody);
	}

}