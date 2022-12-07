package com.geekbrains.spring.web.order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productName;
    private int quantity;
    private int costPerProduct;
    private int cost;



//    public OrderItemDto() {
//        this.id = getId();
//        this.productName = getProductName();
//        this.quantity = getQuantity();
//        this.costPerProduct = getCostPerProduct();
//        this.cost = getCost();
//    }

//    public void changeQuantity(int delta) {
//        this.quantity += delta;
//        this.cost = this.quantity * this.costPerProduct;
//    }

}
