package com.geekbrains.spring.web.order.controllers;

import com.geekbrains.spring.web.order.converters.OrderConverter;
import com.geekbrains.spring.web.order.dto.OrderDto;
import com.geekbrains.spring.web.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;


//    @PostMapping("/{cartName}")
//    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName) {
//        orderService.createOrder(username, orderDetailsDto, cartName);
//    }

    @GetMapping
    public List<OrderDto> getCurrenOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }


}
