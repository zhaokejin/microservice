package cn.cicoding.rocketmq.controller;

import cn.cicoding.rocketmq.producer.OrderEventProducer;
import cn.cicoding.rocketmq.producer.UserNotifyProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息发送 REST API
 * <p>
 * 通过 HTTP 接口触发各类消息发送，便于测试和外部系统调用
 */
@Slf4j
@RestController
@RequestMapping("/rocketmq")
public class NotifyController {

    private final UserNotifyProducer userNotifyProducer;
    private final OrderEventProducer orderEventProducer;

    public NotifyController(UserNotifyProducer userNotifyProducer,
                            OrderEventProducer orderEventProducer) {
        this.userNotifyProducer = userNotifyProducer;
        this.orderEventProducer = orderEventProducer;
    }

    /** 普通消息：用户注册欢迎通知 */
    @PostMapping("/welcome")
    public ResponseEntity<Map<String, Object>> sendWelcome(@RequestParam Long userId,
                                                            @RequestParam String userName) {
        SendResult result = userNotifyProducer.sendWelcomeMessage(userId, userName);
        return ResponseEntity.ok(buildResult("welcome", result));
    }

    /** 顺序消息：同 userId 消息严格按发送顺序消费 */
    @PostMapping("/ordered")
    public ResponseEntity<Map<String, Object>> sendOrdered(@RequestParam Long userId,
                                                            @RequestParam String content) {
        SendResult result = userNotifyProducer.sendOrderedMessage(userId, content);
        return ResponseEntity.ok(buildResult("ordered", result));
    }

    /** 延迟消息：30s（delayLevel=4）后投递 */
    @PostMapping("/delayed")
    public ResponseEntity<Map<String, Object>> sendDelayed(@RequestParam Long userId,
                                                            @RequestParam String content,
                                                            @RequestParam(defaultValue = "4") int delayLevel) {
        SendResult result = userNotifyProducer.sendDelayedMessage(userId, content, delayLevel);
        return ResponseEntity.ok(buildResult("delayed", result));
    }

    /** 订单事件 */
    @PostMapping("/order/created")
    public ResponseEntity<Map<String, Object>> orderCreated(@RequestParam Long orderId,
                                                            @RequestParam Long userId,
                                                            @RequestParam String content) {
        SendResult result = orderEventProducer.sendOrderCreated(orderId, userId, content);
        return ResponseEntity.ok(buildResult("orderCreated", result));
    }

    @PostMapping("/order/paid")
    public ResponseEntity<Map<String, Object>> orderPaid(@RequestParam Long orderId,
                                                          @RequestParam Long userId,
                                                          @RequestParam String content) {
        SendResult result = orderEventProducer.sendOrderPaid(orderId, userId, content);
        return ResponseEntity.ok(buildResult("orderPaid", result));
    }

    private Map<String, Object> buildResult(String type, SendResult result) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", 200);
        body.put("msg", "消息发送成功");
        body.put("type", type);
        body.put("msgId", result.getMsgId());
        body.put("queueId", result.getMessageQueue().getQueueId());
        body.put("queueOffset", result.getQueueOffset());
        return body;
    }
}
