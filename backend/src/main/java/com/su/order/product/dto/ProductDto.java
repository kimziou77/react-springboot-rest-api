package com.su.order.product.dto;

import com.su.order.product.entity.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public interface ProductDto {

    @Data
    @NoArgsConstructor
    class Create{
        private String name;
        private Category category;
        private long price;
        private long quantity;
        private String description;
        private MultipartFile imageMultipart;
    }


    @Data
    @Builder
    @AllArgsConstructor
    class Result{
        private Long id;
        private String name;
        private Category category;
        private long price;
        private long quantity;
        private long count;
        private String imagePath;
        private String description;
        private LocalDateTime createdAt;
    }
}
