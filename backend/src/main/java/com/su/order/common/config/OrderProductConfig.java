package com.su.order.common.config;

import com.su.order.order.repository.OrderRepository;
import com.su.order.orderProduct.repository.OrderProductNamedJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.orderProduct.service.OrderProductService;
import com.su.order.orderProduct.service.SimpleOrderProductService;
import com.su.order.product.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class OrderProductConfig {
    @Bean
    protected OrderProductService orderProductService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository){
        return new SimpleOrderProductService(orderRepository, productRepository,orderProductRepository);
    }

    @Bean
    protected OrderProductRepository orderProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new OrderProductNamedJdbcRepository(namedParameterJdbcTemplate);
    }
}
