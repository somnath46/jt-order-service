package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.order.dto.TransactionRequestDto;
import com.jt.order.dto.TransactionResponseDto;
import com.jt.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/bookOrder")
	public ResponseEntity<TransactionResponseDto> bookOrder(@RequestBody TransactionRequestDto request) {
		return ResponseEntity.ok(orderService.saveOrder(request));
	}
}
