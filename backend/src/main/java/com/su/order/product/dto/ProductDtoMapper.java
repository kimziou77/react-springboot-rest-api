package com.su.order.product.dto;

import com.su.order.product.entity.Product;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductDtoMapper {
    public static Product asProductModel(ProductDto.Create dto){


        return Product.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS))
                .build();
    }
    public static ProductDto.Result asProductResult(Product product){

        return ProductDto.Result.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imagePath(product.getImagePath())
                .description(product.getDescription())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
