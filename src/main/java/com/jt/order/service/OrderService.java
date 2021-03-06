package com.jt.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jt.order.dto.OrderDto;
import com.jt.order.dto.PaymentDto;
import com.jt.order.dto.TransactionRequestDto;
import com.jt.order.dto.TransactionResponseDto;
import com.jt.order.entity.Order;
import com.jt.order.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	@Lazy
	private OrderRepository orderRepository;
	
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String paymentEndpointUri;

	public TransactionResponseDto saveOrder(TransactionRequestDto request) {
		Order order = new Order(request.getOrder().getId(), request.getOrder().getName(), request.getOrder().getQty(),
				request.getOrder().getPrice());
		Order savedOrder = orderRepository.save(order);

		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setOrderId(savedOrder.getId());
		paymentDto.setAmount(savedOrder.getPrice());

//		PaymentDto paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment", paymentDto,
//				PaymentDto.class);
		PaymentDto paymentResponse = restTemplate.postForObject(paymentEndpointUri, paymentDto,
				PaymentDto.class);
		String message = paymentResponse.getPaymentStatus().equals("success") ? "Order placed successfully"
				: "Payment failed, order added to cart";
		return new TransactionResponseDto(
				new OrderDto(savedOrder.getId(), savedOrder.getName(), savedOrder.getQty(), savedOrder.getPrice()),
				paymentResponse.getAmount(), paymentResponse.getTransactionId(), message);
	}
}
