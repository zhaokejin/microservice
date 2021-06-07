package cn.cicoding.microservicesstreamrocketmqproducer.rocketmq.consumer;

import cn.cicoding.microservicesstreamrocketmqproducer.source.MySink;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    RocketMQTemplate kafkaTemplate;

    @PostConstruct
    public void init() {
      System.out.println("MessageConsumer.init()");
      log.debug("MessageConsumer.init()");
    }

    @StreamListener(MySink.MYINPUT)
    public void input1(String payload) {
        log.info("got message: " + payload);
    }

    @StreamListener(Sink.INPUT)
    public void input(String payload) {
        log.info("got message: " + payload);
    }

}