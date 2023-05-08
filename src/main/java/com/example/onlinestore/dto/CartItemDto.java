package com.example.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemDto {
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
