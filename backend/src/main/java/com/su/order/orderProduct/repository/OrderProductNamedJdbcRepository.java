package com.su.order.orderProduct.repository;

import com.su.order.order.entity.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

import static com.su.order.orderProduct.repository.HelperOrderProductRepository.orderProductRowMapper;
import static com.su.order.orderProduct.repository.HelperOrderProductRepository.toOrderProductParamMap;
import static com.su.order.orderProduct.repository.OrderProductSQL.*;

@RequiredArgsConstructor
public class OrderProductNamedJdbcRepository implements OrderProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public OrderProduct insert(OrderProduct orderProduct) {
        KeyHolder orderProductKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT.getSql(), toOrderProductParamMap(orderProduct), orderProductKeyHolder);
        orderProduct.setId(orderProductKeyHolder.getKey().longValue());
        return orderProduct;
    }

    @Override
    public List<OrderProduct> findAll() {
        return jdbcTemplate.query(FIND_ALL.getSql(), orderProductRowMapper);
    }

    @Override
    public int count() {
        var count = jdbcTemplate.getJdbcTemplate().queryForObject(COUNT.getSql(), Integer.class);
        return (count != null) ? count.intValue() : 0;
    }

}
