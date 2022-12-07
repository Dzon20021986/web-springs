package com.geekbrains.spring.web.order.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private String productName;
    private List<OrderItemDto> itemDtoList;
    private Integer totalCost;
    private String address;
    private String phone;

}
