package com.geekbrains.spring.web.product.validators;

//import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ValidationException;
import com.geekbrains.spring.web.product.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getCost() < 1) {
            errors.add("Цена продукта не может быть меньше 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
