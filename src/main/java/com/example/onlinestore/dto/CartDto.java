package com.example.onlinestore.dto;

import com.example.onlinestore.entity.CartItem;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CartDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    private List<CartItemDto> cartItems;
}
