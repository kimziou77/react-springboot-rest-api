package com.su.order.order.controller.dto;

import com.su.order.order.entity.OrderProduct;
import com.su.order.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class UserOrderRequest {
        String email;
        String address;
        List<OrderProduct> orderProducts;
        Long totalPrice;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Result {
        private Long id;
        private String email;
        private String address;
        private Long totalPrice;
        private OrderStatus orderStatus;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
