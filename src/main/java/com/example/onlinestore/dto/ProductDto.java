package com.example.onlinestore.dto;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import lombok.*;

@Builder
@Data
public class ProductDto {
    private String name;
    private String image;
    private String description;
    private int quantity;
    private Double price;

    public static ProductDto from (Product product){
        return builder()
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }
}
