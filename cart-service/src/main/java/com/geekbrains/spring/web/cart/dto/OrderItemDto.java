package com.geekbrains.spring.web.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;  // id продукта
    private String title;  //  название продукта
    private int quantity;  //  количество
    private int costPerProduct;  // цена за единицу
    private int cost;  //  цена

    public OrderItemDto(ProductDto product) {
        this.productId = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.costPerProduct = product.getCost();
        this.cost = product.getCost();
    }

    public void changeQuantity(int delta) {  // изменение количества
        this.quantity += delta;
        this.cost = this.quantity * this.costPerProduct;
    }

}
