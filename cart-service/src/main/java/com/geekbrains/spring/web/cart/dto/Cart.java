package com.geekbrains.spring.web.cart.dto;

import lombok.Data;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<OrderItemDto> items;
    private int totalCost;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String cartName, CacheManager manager) {
        this.items = new ArrayList<>();
        this.totalCost = 0;
    }

    public boolean addProductCount(Long id) {  //
        for (OrderItemDto o : items) {
            if (o.getProductId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addProduct(ProductDto product) { // добавление продукта
        if (addProductCount(product.getId())) {
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    private void recalculate() {  // пересчет всей стоимости корзины
        totalCost = 0;
        for (OrderItemDto o : items) {
            totalCost += o.getCost();
        }
    }

    public void removeProduct(Long id) {  // удаление продукта
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    public void decreaseProduct(Long id) {  // уменьшение количества продуктов
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto o = iter.next();
            if (o.getProductId().equals(id)) {
                o.changeQuantity(-1);
                if (o.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void clear() {  // удаление
        items.clear();
        totalCost = 0;
    }

    public void merge(Cart another) {  //
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
