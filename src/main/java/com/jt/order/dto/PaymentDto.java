package com.jt.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private Integer id;
	private String paymentStatus;
	private String transactionId;
	private Integer orderId;
	private double amount;

}
