package com.su.order.order.repository;

import com.su.order.order.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order insert(Order order);
    List<Order> findAll();

    Optional<Order> findById(Long id);

    int count();

    Order cancel(Order order);

    void deleteById(Long id);
}
