package com.su.order.product.repository;

import com.su.order.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.*;

import static com.su.order.product.repository.HelperProductRepository.productRowMapper;
import static com.su.order.product.repository.HelperProductRepository.toProductParamMap;
import static com.su.order.product.repository.ProductSQL.*;

@RequiredArgsConstructor
public class ProductNamedJdbcRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Product insert(Product product) {
        KeyHolder orderKeyHolder = new GeneratedKeyHolder();

        var update = jdbcTemplate.update(INSERT.getSql(), toProductParamMap(product),orderKeyHolder);
        product.setId(orderKeyHolder.getKey().longValue());

        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(FIND_BY_ID.getSql(), Collections.singletonMap("productId", productId), productRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL.getSql(), productRowMapper);
    }

    @Override
    public Product update(Product product) {
        var update = jdbcTemplate.update(UPDATE.getSql(), toProductParamMap(product));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return product;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.getJdbcTemplate().update(DELETE_BY_ID.getSql(), id.toString());
    }

    @Override
    public long count() {
        var count = jdbcTemplate.getJdbcTemplate().queryForObject(COUNT.getSql(), Integer.class);
        return (count != null) ? count.intValue() : 0;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.getJdbcTemplate().update(DELETE_ALL.getSql());
    }

}
