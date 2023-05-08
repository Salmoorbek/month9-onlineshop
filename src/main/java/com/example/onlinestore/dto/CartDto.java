package com.example.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDto {
    private Long userId;
}
