package com.su.order.order.service;

import com.su.order.order.controller.dto.OrderDtoMapper;
import com.su.order.order.entity.Order;
import lombok.RequiredArgsConstructor;
import com.su.order.order.controller.dto.OrderDto;
import com.su.order.order.repository.OrderRepository;
import com.su.order.orderProduct.repository.OrderProductRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.su.order.order.controller.dto.OrderDtoMapper.asOrderResult;


@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimpleOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public Order createOrder(OrderDto.UserOrderRequest dto) {
        var requestOrder = new Order(dto.getEmail(), dto.getAddress(),dto.getTotalPrice());
        var order = orderRepository.insert(requestOrder);

        dto.getOrderProducts().forEach(orderProduct -> {
           orderProduct.setOrderId(order.getId());
           orderProduct.setCreatedAt(order.getCreatedAt());
           orderProduct.setUpdatedAt(order.getUpdatedAt());
           orderProductRepository.insert(orderProduct);
        });

        order.setOrderProducts(dto.getOrderProducts());
        return order;
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        var optionalOrder = orderRepository.findById(id);
        var order = optionalOrder.get();
        orderRepository.cancel(order);
    }

    @Override
    public OrderDto.Result findById(Long id) {
        var order = orderRepository.findById(id).orElseThrow();
        return asOrderResult(order);
    }

    @Override
    public List<OrderDto.Result> findAll() {
        var orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDtoMapper::asOrderResult)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
