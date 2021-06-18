package com.jt.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
