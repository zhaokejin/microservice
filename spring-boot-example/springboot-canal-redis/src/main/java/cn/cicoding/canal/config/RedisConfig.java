package cn.cicoding.canal.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author cicoding
 * @Description
 * @date 2023/2/25
 */
@Configuration
@AllArgsConstructor
public class RedisConfig {

    private RedisConnectionFactory factory;

    /**
     * 设置json序列化
     *
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // json序列化
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        // key设置json序列化
        redisTemplate.setKeySerializer(serializer);
        // value设置json序列化
        redisTemplate.setValueSerializer(serializer);
        // hash结构key设置json序列化
        redisTemplate.setHashKeySerializer(serializer);
        // hash结构value设置json序列化
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }

}
