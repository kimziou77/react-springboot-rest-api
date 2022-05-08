package com.su.order.common;

import com.su.order.order.repository.OrderJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductNamedJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.order.repository.OrderRepository;
import com.su.order.order.service.OrderService;
import com.su.order.order.service.SimpleOrderService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class OrderServiceConfig {

    @Bean
    public OrderRepository orderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new OrderJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    public OrderService orderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository){
        return new SimpleOrderService(orderRepository, orderProductRepository);
    }
    @Bean
    protected OrderProductRepository orderProductRepository(NamedParameterJdbcTemplate jdbcTemplate){
        return new OrderProductNamedJdbcRepository(jdbcTemplate);
    }
}
