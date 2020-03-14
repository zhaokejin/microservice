package cn.cicoding.rocketmqdemo.producer;
 
import cn.cicoding.rocketmqdemo.entity.UserMsg;
import com.alibaba.fastjson.JSON;
import cn.cicoding.rocketmqdemo.Constant;
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