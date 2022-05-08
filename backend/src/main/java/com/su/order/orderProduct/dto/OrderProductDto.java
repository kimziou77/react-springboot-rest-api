package com.su.order.orderProduct.dto;

import com.su.order.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public interface OrderProductDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class Result {
        private Long id;

        private Long orderId;
        private Long productId;

        private Long count;
        private Long totalPrice;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

    }
}
