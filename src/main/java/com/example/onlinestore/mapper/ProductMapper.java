package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductDto from(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
