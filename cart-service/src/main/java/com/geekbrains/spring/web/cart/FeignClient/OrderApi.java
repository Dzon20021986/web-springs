package com.geekbrains.spring.web.cart.FeignClient;

import com.geekbrains.spring.web.cart.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "order-service", url = "http://localhost:8190/web-market-order/api/v1/orders")
public interface OrderApi {

    @RequestMapping(method = RequestMethod.GET, value = "")
    List<OrderDto> getCurrenOrders();
}
