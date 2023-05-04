package com.example.onlinestore.dto;

import lombok.*;

@Builder
@Data
public class OrderDto {
    private Long userId;
    private Long productId;
    private Long quantity;
}
