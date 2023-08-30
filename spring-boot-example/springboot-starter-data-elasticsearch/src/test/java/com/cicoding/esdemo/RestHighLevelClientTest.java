package com.cicoding.esdemo;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cidoing
 * @version 1.0
 * @date 2020/9/24
 * @since 2020/9/24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestHighLevelClientTest {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testCreateIndex() {

    }
}
