package com.su.order.common.config;

import com.su.order.product.repository.ProductNamedJdbcRepository;
import com.su.order.product.repository.ProductRepository;
import com.su.order.product.service.ProductService;
import com.su.order.product.service.SimpleProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ProductConfig {

    @Bean
    public ProductRepository productRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new ProductNamedJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    public ProductService productService(ProductRepository productRepository){
        return new SimpleProductService(productRepository);
    }
}
