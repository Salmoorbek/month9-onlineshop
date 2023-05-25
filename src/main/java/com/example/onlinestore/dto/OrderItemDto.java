package com.example.onlinestore.dto;

import java.math.BigDecimal;
import lombok.*;
import javax.validation.constraints.*;

@Builder
@Data
public class OrderItemDto {
    @NotNull
    private Long id;
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

}
