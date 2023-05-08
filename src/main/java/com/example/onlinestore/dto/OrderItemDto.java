package com.example.onlinestore.dto;

import java.math.BigDecimal;
import lombok.*;

@Builder
@Data
public class OrderItemDto {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
