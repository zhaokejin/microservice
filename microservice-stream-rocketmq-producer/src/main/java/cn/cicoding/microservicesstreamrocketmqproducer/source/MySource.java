package cn.cicoding.microservicesstreamrocketmqproducer.source;

/**
 * @author zhaokejin
 * @description
 * @date 2020/1/2
 */
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface MySource {

	String MYOUTPUT = "output1";

	@Output
	SubscribableChannel output1();

}
