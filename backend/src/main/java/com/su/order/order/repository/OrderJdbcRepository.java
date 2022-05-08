package com.su.order.order.repository;

import java.util.*;
import lombok.RequiredArgsConstructor;
import com.su.order.order.entity.Order;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import static com.su.order.order.repository.OrderSQL.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import static com.su.order.order.repository.HelperOrderRepository.*;

@RequiredArgsConstructor
public class OrderJdbcRepository implements OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Order insert(Order order) {
        KeyHolder orderKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT.getSql(),toOrderParamMap(order), orderKeyHolder);
        order.setId(orderKeyHolder.getKey());
        return order;
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_ALL.getSql(), orderRowMapper);
    }

    @Override
    public Optional<Order> findById(Long id) {
        try {
            return Optional.of(jdbcTemplate.getJdbcTemplate().queryForObject(FIND_BY_ID.getSql(), orderRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Order cancel(Order order) {
        var update = jdbcTemplate.update(CANCEL.getSql(), cancelOrderParamMap(order));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return order;
    }

    @Override
    public int count() {
        var count = jdbcTemplate.getJdbcTemplate().queryForObject(COUNT.getSql(), Integer.class);
        return (count != null) ? count.intValue() : 0;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.getJdbcTemplate().update(DELETE_BY_ID.getSql(), id);
    }

}
