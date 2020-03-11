package cn.cicoding.microservicesstreamrocketmqproducer.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "rocketmq_transaction_log")
public class RocketmqTransactionLog {
	/**
	 * id
	 */
//	@Id
//	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 事务id
	 */
//	@Column(name = "transaction_Id")
	private String transactionId;

	/**
	 * 日志
	 */
	private String log;
}