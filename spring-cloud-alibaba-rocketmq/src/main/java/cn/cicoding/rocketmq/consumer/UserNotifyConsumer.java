package cn.cicoding.rocketmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 用户通知 Consumer
 * <p>
 * 演示：集群消费模式 + 消费重试
 * <p>
 * 注意：所有 tag 都用 * 接收，Consumer 内部按业务逻辑分发
 */
@Slf4j
@Component
@RocketMQMessageListener(
        nameServer = "${rocketmq.name-server}",
        topic = "${rocketmq.topic.user-notify}",
        selectorExpression = "*",
        consumerGroup = "rocketmq-user-notify-consumer",
        messageModel = MessageModel.CLUSTERING,
        maxReconsumeTimes = 3
)
public class UserNotifyConsumer implements RocketMQListener<MessageExt> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(MessageExt message) {
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        String tags = message.getTags();
        log.info("[Consumer] 收到消息 topic={}, tag={}, keys={}, body={}",
                message.getTopic(), tags, message.getKeys(), body);

        try {
            // 模拟业务处理
            Map<String, Object> payload = objectMapper.readValue(body, Map.class);
            switch (tags) {
                case "welcome" -> handleWelcome(payload);
                case "orderCreated" -> handleOrderCreated(payload);
                case "orderPaid" -> handleOrderPaid(payload);
                default -> log.warn("[Consumer] 未知 tag={}，跳过", tags);
            }
        } catch (JsonProcessingException e) {
            // 业务异常 → 抛出触发重试
            log.error("[Consumer] 消息反序列化失败 keys={}", message.getKeys(), e);
            throw new RuntimeException("消息反序列化失败", e);
        }
    }

    private void handleWelcome(Map<String, Object> payload) {
        log.info("[业务] 发送欢迎邮件/短信给 userId={}", payload.get("userId"));
        // 实际场景：调用邮件服务 / 短信服务
    }

    private void handleOrderCreated(Map<String, Object> payload) {
        log.info("[业务] 订单创建事件 userId={} content={}", payload.get("userId"), payload.get("content"));
    }

    private void handleOrderPaid(Map<String, Object> payload) {
        log.info("[业务] 订单支付事件 userId={} content={}", payload.get("userId"), payload.get("content"));
    }
}
