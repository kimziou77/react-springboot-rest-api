package com.su.order.order.controller;

import com.su.order.order.repository.OrderRepository;
import com.su.order.orderProduct.service.OrderProductService;
import com.su.order.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.su.order.order.controller.dto.OrderDtoMapper.asOrderResult;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    /* 주문 목록 조회 */
    @GetMapping("/orders")
    public String productsPage(Model model) {
        var orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/order-list";
    }

    /* 주문 상세 조회 */
    @GetMapping("orders/{id}")
    public String orderDetail(@PathVariable Long id, Model model){
        var order = orderService.findById(id);
        var orderProducts = orderProductService.getOrderProduct(id);
        var products = orderProductService.getProducts(orderProducts);

        model.addAttribute("order", order);
        model.addAttribute("orderProducts", orderProducts);
        model.addAttribute("products",products);
        return "order/order-detail";
    }

    /* 주문 취소 */
    @PutMapping("orders/{id}")
    public String cancelOrder(@PathVariable Long id){
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }

    /* 주문 삭제 */
    @DeleteMapping("orders/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteById(id);
        return "redirect:/orders";
    }


}
