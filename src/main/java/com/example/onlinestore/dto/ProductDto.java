package com.example.onlinestore.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String imageUrl;
    private String description;
    private String category;
    private Integer quantity;
    private BigDecimal price;
}
