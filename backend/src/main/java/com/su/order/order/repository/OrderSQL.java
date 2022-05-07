package com.su.order.order.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderSQL {
    INSERT("INSERT INTO orders(email, address, total_price, order_status, created_at, updated_at) " +
            "VALUES (:email, :address, :totalPrice, :orderStatus, :createdAt, :updatedAt)"),
    FIND_ALL("select * from orders"),
    FIND_BY_ID("SELECT * from orders where id = ?"),
    COUNT("select count(*) from orders"),
    CANCEL("UPDATE orders SET order_status=:orderStatus, updated_at=:updatedAt WHERE id = :id"),
    DELETE_BY_ID("DELETE FROM orders WHERE id = ?");

    private final String sql;
}
