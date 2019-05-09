package com.example.rocketmqdemo.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 *  订单消费者
 */
public class RocketMqConsumer {
    private static String customerGroup = "rocketMQCustomer";
    private static String namesrvAddr = "127.0.0.1:9876";
    private static String topic = "userOrder";
    private static String tag = "order";
 
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(customerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
 
        // TODO: 2019/3/8 这里可以设置消费消息的位置
        /**
         * CONSUME_FROM_FIRST_OFFSET
         * CONSUME_FROM_LAST_OFFSET
         * 同时会记录消息消息的offset
         * */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // TODO:  subExpression 还可以这样写
        /**
         * tag >>>  "order || repay"
         * */
        consumer.subscribe(topic, tag);
 
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> messageExts, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
 
                for (MessageExt messageExt: messageExts) {
                    System.out.println("consumer >>> " + new String(messageExt.getBody()));
                }
 
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("开始消费消息！！！");
    }
 
 
}