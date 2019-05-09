package com.example.rocketmqdemo.producer;
 
import com.alibaba.fastjson.JSON;
import com.example.rocketmqdemo.Constant;
import com.example.rocketmqdemo.entity.UserMsg;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
/**
 *
 */
@Component
public class MsgProducer {
 
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
 
    public void sendUserMsg(UserMsg userMsg){
        rocketMQTemplate.syncSend(Constant.TOPIC, JSON.toJSONString(userMsg));
    }
}