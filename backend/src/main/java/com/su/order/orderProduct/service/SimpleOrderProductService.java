package com.su.order.orderProduct.service;

import com.su.order.order.entity.OrderProduct;
import com.su.order.order.repository.OrderRepository;
import com.su.order.order.service.OrderService;
import com.su.order.orderProduct.dto.OrderProductDto;
import com.su.order.orderProduct.dto.OrderProductDtoMapper;
import com.su.order.orderProduct.repository.OrderProductRepository;
import com.su.order.product.dto.ProductDto;
import com.su.order.product.dto.ProductDtoMapper;
import com.su.order.product.entity.Product;
import com.su.order.product.repository.ProductRepository;
import com.su.order.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.su.order.product.dto.ProductDtoMapper.asProductResult;

@RequiredArgsConstructor
public class SimpleOrderProductService implements OrderProductService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;


    @Override
    public List<OrderProductDto.Result> getOrderProduct(Long id) {
        var orderProducts = orderProductRepository.findAll();
        return orderProducts.stream()
                .filter(orderProduct -> orderProduct.getOrderId()==id)
                .map(OrderProductDtoMapper::asOrderProductResult)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto.Result> getProducts(List<OrderProductDto.Result> orderProducts) {
        List<ProductDto.Result> products = new ArrayList<>();
        orderProducts.forEach(orderProduct->{
            var product = productRepository.findById(orderProduct.getProductId()).orElseThrow();
            var dto = asProductResult(product);
            dto.setCount(orderProduct.getCount());
            products.add(dto);
        });
        return products;
    }
}
