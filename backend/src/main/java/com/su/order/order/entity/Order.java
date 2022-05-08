package com.su.order.order.entity;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@ToString
public class Order {
    private Long id;

    private String email;
    private String address;

    private List<OrderProduct> orderProducts;

    private Long totalPrice;

    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // request 용 생성자 (UserInfo)
    public Order(String email, String address, Long totalPrice) {
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderStatus = OrderStatus.ACCEPTED;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    // 상품 목록 제외 생성자
    public Order(Long id, String email, String address,Long totalPrice, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // setter
    public void setOrderProducts(List<OrderProduct> orderProducts){
        if(this.orderProducts==null){
            this.orderProducts = orderProducts;
            return;
        }
        throw new RuntimeException("OrderProducts 재할당!");
    }

    public void setId(Number id){
        this.id = id.longValue();
    }
}