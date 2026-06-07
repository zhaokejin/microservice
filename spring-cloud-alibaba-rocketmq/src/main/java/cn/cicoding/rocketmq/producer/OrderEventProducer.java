package cn.cicoding.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 订单事件 Producer
 * <p>
 * 演示事务消息：half 消息 + 本地事务执行 + Commit/Rollback
 */
@Slf4j
@Component
public class OrderEventProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic.order-event}")
    private String orderEventTopic;

    public OrderEventProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 发送订单创建事件（普通消息）
     */
    public SendResult sendOrderCreated(Long orderId, Long userId, String content) {
        return send(orderEventTopic + ":orderCreated", orderId, userId, content);
    }

    /**
     * 发送订单支付事件（普通消息）
     */
    public SendResult sendOrderPaid(Long orderId, Long userId, String content) {
        return send(orderEventTopic + ":orderPaid", orderId, userId, content);
    }

    private SendResult send(String destination, Long orderId, Long userId, String content) {
        String payload = String.format("{\"orderId\":%d,\"userId\":%d,\"content\":\"%s\"}",
                orderId, userId, content);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.KEYS, String.valueOf(orderId))
                .setHeader("orderId", orderId)
                .build();
        SendResult result = rocketMQTemplate.syncSend(destination, message);
        log.info("[订单事件] destination={}, orderId={}, sendResult={}", destination, orderId, result);
        return result;
    }
}
