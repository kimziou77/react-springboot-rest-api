package com.su.order.common.config;

import com.su.order.order.repository.OrderJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductNamedJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.order.repository.OrderRepository;
import com.su.order.order.service.OrderService;
import com.su.order.order.service.SimpleOrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new OrderJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    public OrderService orderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository){
        return new SimpleOrderService(orderRepository, orderProductRepository);
    }
}
