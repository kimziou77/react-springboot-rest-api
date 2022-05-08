package com.su.order.orderProduct.repository;

import com.su.order.order.entity.Order;
import com.su.order.order.entity.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    OrderProduct insert(OrderProduct orderProduct);
    List<OrderProduct> findAll();
    int count();
}
