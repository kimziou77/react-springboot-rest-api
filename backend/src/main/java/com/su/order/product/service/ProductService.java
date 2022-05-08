package com.su.order.product.service;

import com.su.order.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();

    void createProduct(Product product);

    void deleteById(Long id);
}
