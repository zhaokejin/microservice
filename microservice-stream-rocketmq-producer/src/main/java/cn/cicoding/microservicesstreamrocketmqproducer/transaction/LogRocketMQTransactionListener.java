package cn.cicoding.microservicesstreamrocketmqproducer.transaction;

import cn.cicoding.microservicesstreamrocketmqproducer.bean.RocketmqTransactionLog;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * https://github.com/alibaba/spring-cloud-alibaba/wiki/RocketMQ
 * @author zhaokejin
 * @description
 * @date 2019/11/14
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogRocketMQTransactionListener implements RocketMQLocalTransactionListener {


//	private final ShareService shareService;
//	private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
		MessageHeaders headers = message.getHeaders();
		String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
		Integer shareId = Integer.valueOf((String) headers.get("share_id"));

//		String dto = (String) headers.get("dto");
//		ShareAuditDTO shareAuditDTO = JSON.parseObject(dto, ShareAuditDTO.class);

		try {
//			this.shareService.auditByIdWithRocketMqLog(shareId, shareAuditDTO, transactionId);
			return RocketMQLocalTransactionState.COMMIT;
		} catch (Exception e) {
			return RocketMQLocalTransactionState.ROLLBACK;
		}

	}

	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
		MessageHeaders headers = message.getHeaders();
		String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);

		RocketmqTransactionLog transactionLog = null;/*this.rocketmqTransactionLogMapper.selectOne(RocketmqTransactionLog.builder().transactionId(transactionId).build());*/
		if (transactionLog != null) {
			return RocketMQLocalTransactionState.COMMIT;
		}

		return RocketMQLocalTransactionState.ROLLBACK;
	}
}
