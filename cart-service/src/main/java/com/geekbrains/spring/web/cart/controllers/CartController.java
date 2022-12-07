package com.geekbrains.spring.web.cart.controllers;

import com.geekbrains.spring.web.cart.dto.OrderDetailsDto;
import com.geekbrains.spring.web.cart.services.CartService;
import com.geekbrains.spring.web.cart.dto.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping
    public Cart getCurrentCart(@RequestBody String cartName) {  // получить текущую корзину
        return service.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")   // добавить товар в корзину по id
    public void addProductToCart(@PathVariable Long id, @RequestBody String cartName) {
        service.addProductByIdToCart(id, cartName);
    }

    @PostMapping("/clear")  // удаление
    public void clearCart(@RequestBody String cartName) {
        service.clear(cartName);
    }

    @PostMapping("/createOrder")  // создание заказа
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName) {
        service.createOrder(username, orderDetailsDto, cartName);
    }

    @PostMapping("/removeProduct")
    public void removeProduct(@PathVariable Long id, @RequestBody String cartName) {
        service.removeProduct(id, cartName);
    }

}
