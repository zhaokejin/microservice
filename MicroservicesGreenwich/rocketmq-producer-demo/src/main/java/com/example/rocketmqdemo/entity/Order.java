package com.example.rocketmqdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author zhaokejin
 * @create 2019/5/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private String orderNo;
	private String productName;
	private BigDecimal unitPrice;
	private String createDate;

	private Order(Builder builder) {
		setOrderNo(builder.orderNo);
		setProductName(builder.productName);
		setUnitPrice(builder.unitPrice);
		setCreateDate(builder.createDate);
	}


	public static final class Builder {
		private String orderNo;
		private String productName;
		private BigDecimal unitPrice;
		private String createDate;

		public Builder() {
		}

		public Builder orderNo(String val) {
			orderNo = val;
			return this;
		}

		public Builder productName(String val) {
			productName = val;
			return this;
		}

		public Builder unitPrice(BigDecimal val) {
			unitPrice = val;
			return this;
		}

		public Builder createDate(String val) {
			createDate = val;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}
}