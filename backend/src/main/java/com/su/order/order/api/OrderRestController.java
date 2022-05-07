package com.su.order.order.api;

import com.su.order.order.controller.dto.OrderDto;
import com.su.order.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.su.order.order.controller.dto.OrderDtoMapper.asOrderResult;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    // 주문 생성 API
    @PostMapping("/orders")
    public ResponseEntity<OrderDto.Result> createOrder(@Validated @RequestBody OrderDto.UserOrderRequest dto){
        var order = orderService.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(asOrderResult(order));
    }
}
