package com.example.rocketmqdemo.test;
 
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author zhaokejin
 */
public class Producer {
	public static void main(String[] args) throws MQClientException, InterruptedException {
//		//声明并初始化一个producer
//        //需要一个producer group名字作为构造方法的参数，这里为producer1
//        DefaultMQProducer producer = new DefaultMQProducer("test");
//
//        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
//        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
//        producer.setNamesrvAddr("127.0.0.1:9876");
//   //     producer.setVipChannelEnabled(false);//3.2。6的版本没有该设置，在更新或者最新的版本中务必将其设置为false，否则会有问题
//        //调用start()方法启动一个producer实例
//        producer.start();
//
//        //发送10条消息到Topic为TopicTest，tag为TagA，消息内容为“Hello RocketMQ”拼接上i的值
//        for (int i = 0; i < 10; i++) {
//            try {
//                // topic
//                Message msg = new Message("TopicTest",
//                        // tag
//                        "TagA",
//                        // body
//                        ("Hello RocketMQ " + i).getBytes("utf-8")
//                );
//
//                //调用producer的send()方法发送消息
//                //这里调用的是同步的方式，所以会有返回结果
//                SendResult sendResult = producer.send(msg);
//                //发送结果状态
//                System.out.println(sendResult.getSendStatus());
//                //打印返回结果，可以看到消息发送的状态以及一些相关信息
//                System.out.println(sendResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Thread.sleep(1000);
//            }
//        }
//
//        //发送完消息之后，调用shutdown()方法关闭producer
//        producer.shutdown();

        DefaultMQProducer producer = new DefaultMQProducer("producer_demo");
        //指定NameServer地址
        //修改为自己的
        producer.setNamesrvAddr("10.254.193.32:9876;10.254.193.30:9876");

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

        for (int i = 0; i < 9999; i++) {
            try {
                //构建消息
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("测试RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );

                //发送同步消息
                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }


        producer.shutdown();
        System.out.println("完成！");

	}
}