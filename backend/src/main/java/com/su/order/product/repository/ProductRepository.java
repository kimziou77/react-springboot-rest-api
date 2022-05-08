package com.su.order.product.repository;

import com.su.order.product.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll();
    Product insert(Product product);
    Product update(Product product);
    Optional<Product> findById(Long productId);
    void deleteAll();
    void deleteById(Long id);

    long count();
}
