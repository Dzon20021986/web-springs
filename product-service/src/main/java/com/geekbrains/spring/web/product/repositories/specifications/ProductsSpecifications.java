package com.geekbrains.spring.web.product.repositories.specifications;

import com.geekbrains.spring.web.product.enttities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductsSpecifications {
    public static Specification<com.geekbrains.spring.web.product.enttities.Product> costGreaterOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<com.geekbrains.spring.web.product.enttities.Product> costLessThanOrEqualsThan(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
