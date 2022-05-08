package com.su.order.order.service;

import com.su.order.order.controller.dto.OrderDto;
import com.su.order.order.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDto.UserOrderRequest dto);
    void cancelOrder(Long id);
    OrderDto.Result findById(Long id);

    List<OrderDto.Result> findAll();

    void deleteById(Long id);
}
