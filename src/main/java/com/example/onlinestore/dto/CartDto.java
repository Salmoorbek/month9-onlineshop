package com.example.onlinestore.dto;

import com.example.onlinestore.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    private List<CartItemDto> cartItems;
}
