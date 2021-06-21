package com.jt.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

	private OrderDto order;
	private Double amount;
	private String transactionId;
	private String message;
}
