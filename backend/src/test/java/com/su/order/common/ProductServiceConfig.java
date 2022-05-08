package com.su.order.common;

import com.su.order.product.repository.ProductNamedJdbcRepository;
import com.su.order.product.repository.ProductRepository;
import com.su.order.product.service.ProductService;
import com.su.order.product.service.SimpleProductService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class ProductServiceConfig {

    @Bean
    public ProductRepository productRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new ProductNamedJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    public ProductService productService(ProductRepository productRepository){
        return new SimpleProductService(productRepository);
    }
}
