package com.geekbrains.spring.web.order.converters;

import com.geekbrains.spring.web.order.dto.OrderItemDto;
import com.geekbrains.spring.web.order.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {


    public OrderItemDto entityToDto(OrderItem orderItem) {
        return  new OrderItemDto(orderItem.getId(), orderItem.getProductName(),
                orderItem.getQuantity(), orderItem.getCostPerProduct(), orderItem.getCost());
    }

}



