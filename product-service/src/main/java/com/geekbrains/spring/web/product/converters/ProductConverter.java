package com.geekbrains.spring.web.product.converters;

import com.geekbrains.spring.web.product.dto.ProductDto;
import com.geekbrains.spring.web.product.enttities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

}
