package cn.cicoding.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 用户通知 Producer
 * <p>
 * 演示三类典型消息：
 * 1. 普通消息（同步发送）
 * 2. 顺序消息（hashKey 路由到固定队列）
 * 3. 延迟消息（设置 delayLevel）
 */
@Slf4j
@Component
public class UserNotifyProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic.user-notify}")
    private String userNotifyTopic;

    @Value("${rocketmq.tag.welcome}")
    private String welcomeTag;

    public UserNotifyProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 发送普通消息：用户注册成功异步通知
     */
    public SendResult sendWelcomeMessage(Long userId, String userName) {
        String payload = String.format("{\"userId\":%d,\"userName\":\"%s\",\"msgType\":\"welcome\"}", userId, userName);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.KEYS, UUID.randomUUID().toString())
                .setHeader("userId", userId)
                .build();
        // 同步发送，destination = topic:tag
        SendResult result = rocketMQTemplate.syncSend(userNotifyTopic + ":" + welcomeTag, message);
        log.info("[普通消息] 用户欢迎通知已发送 topic={}, sendResult={}", userNotifyTopic, result);
        return result;
    }

    /**
     * 发送顺序消息：按 userId 路由到固定队列，保证同用户消息顺序
     */
    public SendResult sendOrderedMessage(Long userId, String content) {
        String payload = String.format("{\"userId\":%d,\"content\":\"%s\"}", userId, content);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.KEYS, String.valueOf(userId))
                .build();
        // 第 3 个参数为 hashKey：相同 hashKey 的消息会进入同一个 MessageQueue
        SendResult result = rocketMQTemplate.syncSendOrderly(
                userNotifyTopic, message, String.valueOf(userId));
        log.info("[顺序消息] userId={} 已发送，路由到固定队列", userId);
        return result;
    }

    /**
     * 发送延迟消息：用户注册后 30 分钟未登录则发提醒
     * <p>
     * RocketMQ 延迟级别：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 4 = 30s
     */
    public SendResult sendDelayedMessage(Long userId, String content, int delayLevel) {
        String payload = String.format("{\"userId\":%d,\"content\":\"%s\"}", userId, content);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.KEYS, UUID.randomUUID().toString())
                .build();
        SendResult result = rocketMQTemplate.syncSend(userNotifyTopic, message, 3000, delayLevel);
        log.info("[延迟消息] userId={} 已发送，delayLevel={}（约 {} 秒后投递）",
                userId, delayLevel, delayLevelToSeconds(delayLevel));
        return result;
    }

    private int delayLevelToSeconds(int level) {
        int[] seconds = {0, 1, 5, 10, 30, 60, 120, 180, 240, 300, 360, 420, 480, 540, 600, 1200, 1800, 3600, 7200};
        return level < seconds.length ? seconds[level] : -1;
    }
}
