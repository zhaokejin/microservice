package cn.cicoding.rocketmqdemo.test;
 
import java.util.List;
 
import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
 
public class Consumer {
	public static void main(String[] args) throws MQClientException {
		
//		//声明并初始化一个consumer
//	     //需要一个consumer group名字作为构造方法的参数，这里为consumer1
//	     DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer1");
//
//	     //同样也要设置NameServer地址
//	     consumer.setNamesrvAddr("10.254.193.32:9876;10.254.193.31:9876:9876;10.254.193.30:9876;10.254.193.34:9876");
//
//	     //这里设置的是一个consumer的消费策略
//	     //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
//	     //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
//	     //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
//	     consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//
//	     //设置consumer所订阅的Topic和Tag，*代表全部的Tag
//	     consumer.subscribe("TopicTest", "*");
//
//	     //设置一个Listener，主要进行消息的逻辑处理
//	     consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//	         @Override
//	         public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//	                                                         ConsumeConcurrentlyContext context) {
//
//	            // System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
//	        	 for (MessageExt messageExt : msgs) {
//
//              //       System.out.println("messageExt: " + messageExt);//输出消息内容
//                    String messageBody = new String(messageExt.getBody());
//         //            System.out.println(messageExt.getStoreHost());//获取对应rocketmq服务器ip地址
//					 //输出消息内容
//	        		 System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);
//                 }
//
//	             //返回消费状态
//	             //CONSUME_SUCCESS 消费成功
//	             //RECONSUME_LATER 消费失败，需要稍后重新消费
//	             return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//	         }
//	     });
//
//	     //调用start()方法启动consumer
//	     consumer.start();
//
//	     System.out.println("Consumer Started.");



		/**
		 * Consumer Group,非常重要的概念，后续会慢慢补充
		 */
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_demo");
//指定NameServer地址，多个地址以 ; 隔开
		//修改为自己的
		consumer.setNamesrvAddr("10.254.193.32:9876;10.254.193.30:9876");

/**
 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
 * 如果非第一次启动，那么按照上次消费的位置继续消费
 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		consumer.subscribe("TopicTest", "*");

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
															ConsumeConcurrentlyContext context) {
				try {
					for(MessageExt msg:msgs){
						String msgbody = new String(msg.getBody(), "utf-8");
						System.out.println("  MessageBody: "+ msgbody);//输出消息内容
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
			}
		});


		consumer.start();

		System.out.printf("Consumer Started.%n");
	 }
	 
}