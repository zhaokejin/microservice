package cn.cicoding.rocketmqdemo.test;
 
import cn.cicoding.rocketmqdemo.entity.Order;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
 
/**
 *  订单提供者
 */
public class RocketMqProducer {

    /**
     * 消费组
     */
    private static String producerGroup = "rocketMQProducer";
    /**
     * RocketMq server 地址，多个地址之间用分号分割：192.0.0.1:9876;192.0.0.2:9876
     */
    private static String namesrvAddr = "127.0.0.1:9876";
    //消息主题
    private static String topic = "userOrder";
    //消息类型
    private static String tag = "order";
 
    public static void main(String[] args) throws IOException {
 
        try {
            DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(namesrvAddr);
            producer.start();
 
            List<Order> orderList =  buildOrders();
 
            for (int i = 0; i < orderList.size(); i++) {
 
                String body = "订单信息 >>> " + orderList.get(i);
                //封装消息
                Message message = new Message(topic, tag, "KEY" + i, body.getBytes());
 
                SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message meg, Object arg) {
                        Long id = (Long) arg;
                        long index = id % mqs.size();
                        return mqs.get((int)index);
                    }
                    // TODO: 要保证消息顺序不混乱，必须在生产时将消息push到同一个队列即可
                }, 0L);
 
                System.out.println("producer >>> " + body+" ,发送状态 >>> " + sendResult.getSendStatus());
            }
            producer.shutdown();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static List<Order> buildOrders() {
        List<Order> orderList = new ArrayList<Order>();
        String[] productName = new String[]{
        "Apple iPhone XS Max (A2104) 256GB",
        "HUAWEI Mate 20",
        "三星 Galaxy S10 8GB+512GB",
        "荣耀 8X",
        "小米 9",
        "小米Mix3",
        "荣耀 V20"};
 
        for(int i = 0; i < 7; i++){
            Order order = new Order.Builder().orderNo(UUID.randomUUID().toString().replace("-",""))
                    .productName(productName[new Random().nextInt(7)])
                    .unitPrice(new BigDecimal((new Random().nextInt(10) + 1) * 1000))
                    .createDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .build();
            orderList.add(order);
        }
        return orderList;
    }
}