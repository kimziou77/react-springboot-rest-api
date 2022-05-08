package com.su.order.orderProduct.dto;

import com.su.order.order.entity.OrderProduct;

public class OrderProductDtoMapper {
    public static OrderProductDto.Result asOrderProductResult(OrderProduct product){
        return OrderProductDto.Result.builder()
                .id(product.getId())
                .orderId(product.getProductId())
                .productId(product.getProductId())
                .count(product.getCount())
                .totalPrice(product.getTotalPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
