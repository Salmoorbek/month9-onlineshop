package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public static CartItemDto from(CartItem cart) {
        return CartItemDto.builder()
                .id(cart.getId())
                .cartId(cart.getCart().getId())
                .productId(cart.getProduct().getId())
                .quantity(cart.getQuantity())
                .price(cart.getPrice())
                .build();
    }
}
