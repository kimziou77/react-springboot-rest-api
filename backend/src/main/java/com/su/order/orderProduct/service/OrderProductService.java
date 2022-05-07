package com.su.order.orderProduct.service;

import com.su.order.order.entity.OrderProduct;
import com.su.order.orderProduct.dto.OrderProductDto;
import com.su.order.product.dto.ProductDto;
import com.su.order.product.entity.Product;

import java.util.List;

public interface OrderProductService {
    List<OrderProductDto.Result> getOrderProduct(Long id);

    List<ProductDto.Result> getProducts(List<OrderProductDto.Result> orderProducts);
}
