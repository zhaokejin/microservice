package cn.cicoding.microservicesstreamrocketmqproducer.source;

/**
 * @author zhaokejin
 * @description
 * @date 2020/1/2
 */
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySource {

	//接收队列1
	String MYOUTPUT = "MyOutput";

	@Input(MySource.MYOUTPUT)
	SubscribableChannel input1();

}
