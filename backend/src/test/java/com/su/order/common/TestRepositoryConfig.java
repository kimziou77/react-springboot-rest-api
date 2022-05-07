package com.su.order.common;

import com.su.order.order.repository.OrderJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductNamedJdbcRepository;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.order.repository.OrderRepository;
import com.su.order.product.repository.ProductNamedJdbcRepository;
import com.su.order.product.repository.ProductRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@TestConfiguration
public class TestRepositoryConfig {
    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    OrderRepository orderRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new OrderJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    OrderProductRepository orderProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new OrderProductNamedJdbcRepository(namedParameterJdbcTemplate);
    }
    @Bean
    ProductRepository productRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new ProductNamedJdbcRepository(namedParameterJdbcTemplate);
    }
}
