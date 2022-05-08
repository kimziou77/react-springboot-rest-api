package com.su.order.product.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product {
    private Long id;

    private String name;
    private Category category;
    private long price;
    private long quantity;
    private String description;
    private String imagePath;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(Long id, String name, Category category, long price, long quantity, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product(String name, Category category, long price, long quantity, String description,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setImagePath(String path){
        this.imagePath = path;
    }
}
