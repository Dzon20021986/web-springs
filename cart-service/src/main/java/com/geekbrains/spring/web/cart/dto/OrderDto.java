package com.geekbrains.spring.web.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> itemDtoList;
    private Integer totalCost;
    private String address;
    private String phone;
}
