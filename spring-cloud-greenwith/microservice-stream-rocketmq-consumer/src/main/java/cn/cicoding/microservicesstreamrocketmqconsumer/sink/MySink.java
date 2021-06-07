package cn.cicoding.microservicesstreamrocketmqconsumer.sink;

/**
 * @author zhaokejin
 * @description
 * @date 2020/1/2
 */
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

	//接收队列1
	String MYINPUT = "MyInput";

	@Input(MySink.MYINPUT)
	SubscribableChannel input1();
}
