package cn.cicoding;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Resource
    RedisUtils redisUtils;

    @Test
    void contextLoads() {
        int dbIndex = 14;
        String key = "ttttt";
        final String s1 = redisUtils.get(key, dbIndex);
        System.out.println("s1 = " + s1);
        redisUtils.set(key, "test", 14, 120);
        System.out.println(redisUtils.get(key, dbIndex));
        redisUtils.set(key + "1", key, 4, 60 * 3);
    }


}