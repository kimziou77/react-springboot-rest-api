package com.su.order.order.controller.dto;

import com.su.order.order.entity.Order;

public class OrderDtoMapper {
    public static OrderDto.Result asOrderResult(Order order){
        return OrderDto.Result.builder()
                .id(order.getId())
                .email(order.getEmail())
                .address(order.getAddress())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
