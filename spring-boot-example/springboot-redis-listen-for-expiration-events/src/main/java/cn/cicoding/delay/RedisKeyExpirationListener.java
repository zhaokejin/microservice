package cn.cicoding.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获取过期的key
        String expiredKey = message.toString();
        // 判断是否是想要监听的过期key
        if (expiredKey.contains("TIME_KEY")) {
            log.info("redis key过期：{}", expiredKey);
            // TODO 业务逻辑
        }
    }

}