package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductDto fromPerson(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
