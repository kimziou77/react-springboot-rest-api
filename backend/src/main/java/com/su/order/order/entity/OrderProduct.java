package com.su.order.order.entity;

import com.su.order.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private Long id;

    private Long orderId;
    private Long productId;

    private Long count;
    private Long totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderProduct(Long productId, Long count, Long totalPrice) {
        this.productId = productId;
        this.count = count;
        this.totalPrice = totalPrice;
    }
}
