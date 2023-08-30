package com.abc.pps;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class SomeTest {
    public static void main(String[] args) {

        SubmissionPublisher<Integer> publisher = null;
        try {
            // 创建发布者
            publisher = new SubmissionPublisher<>();
            // 创建处理器
            SomeProcessor processor = new SomeProcessor();
            // 创建订阅者
            SomeSubscriber subscriber = new SomeSubscriber();

            // 建立订阅关系
            publisher.subscribe(processor);
            processor.subscribe(subscriber);

            // 生产消费
            for (int i = 0; i < 300; ++i) {
                // 生成一个[0,100)的随机数
                int item = new Random().nextInt(100);
                System.out.println("生产出第" + i + "条消息：" + item);
                // 发布消息
                publisher.submit(item);
            }
        } finally {
            // 当所有消息发布完毕，关闭发布者
            if(publisher != null) publisher.close();
        }

        try {
            System.out.println("主线程开始等待");
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
