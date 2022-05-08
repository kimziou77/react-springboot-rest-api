package com.su.order.orderProduct.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderProductSQL {
    INSERT("INSERT INTO order_product(order_id, product_id, count, total_price, created_at, updated_at) " +
            "VALUES (:orderId, :productId, :count, :totalPrice, :createdAt, :updatedAt)"),
    FIND_ALL("select * from order_product"),
    COUNT("select count(*) from order_product");

    private final String sql;

}
