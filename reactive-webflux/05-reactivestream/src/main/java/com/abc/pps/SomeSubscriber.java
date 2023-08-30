package com.abc.pps;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * 订阅者
 */
public class SomeSubscriber implements Flow.Subscriber<String> {
    // 声明订阅令牌
    private Flow.Subscription subscription;

    // 当订阅关系确立时会触发该方法的执行，即publisher的subscribe()方法的执行
    // 会触发该方法的执行
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("=== 执行订阅者的onSubscribe()方法 ===");
        this.subscription = subscription;
        // 首次订阅8条消息
        this.subscription.request(8);
    }

    // 对消息的消费过程就在这里
    // 该方法的第一次触发是由onSubscribe()方法中的 this.subscription.request(8); 触发
    @Override
    public void onNext(String item) {
        System.out.println("==================== 订阅者正在消费该item【" + item + "】");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 订阅者每消费一条消息，就会再订阅10条消息
        this.subscription.request(10);
    }

    // 当发布者发布过程中，或订阅关系确立过程中，或订阅者请求消息过程中，或消费者消费过程中
    // 出现异常，则会触发该方法的执行。该方法的执行会导致onNext()方法不再执行
    @Override
    public void onError(Throwable throwable) {
        System.out.println(" --- 执行onError()方法 ---");
        // throwable.printStackTrace();
        // 取消订阅关系
        this.subscription.cancel();
    }

    // 当所有消息消费完毕后会执行该方法
    @Override
    public void onComplete() {
        System.out.println("发布者已关闭，订阅者已将消息全部消费完毕");
    }
}
