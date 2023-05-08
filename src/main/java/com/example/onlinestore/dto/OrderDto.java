package com.example.onlinestore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class OrderDto {
    private Long userId;
    private LocalDateTime createdAt;
    private String status;
    private BigDecimal total;
}
