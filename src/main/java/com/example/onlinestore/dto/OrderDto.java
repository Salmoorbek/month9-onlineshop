package com.example.onlinestore.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class OrderDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    private String status;

    @DecimalMin("0.0")
    private BigDecimal total;
}
