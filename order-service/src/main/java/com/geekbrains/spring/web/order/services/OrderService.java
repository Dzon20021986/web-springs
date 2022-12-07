package com.geekbrains.spring.web.order.services;

import com.geekbrains.spring.web.order.dto.OrderDto;
import com.geekbrains.spring.web.order.entities.Order;
import com.geekbrains.spring.web.order.entities.OrderItem;
import com.geekbrains.spring.web.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setUsername(orderDto.getUsername());
        order.setTotalCost(orderDto.getTotalCost());
        List<OrderItem> items = orderDto.getItemDtoList().stream()
                .map(o -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(o.getQuantity());
                    orderItem.setCostPerProduct(o.getCostPerProduct());
                    orderItem.setCost(o.getCost());
                    orderItem.setProductName(o.getProductName());
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

//    public Order findById(Long id){
//        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException());
//    }

}
