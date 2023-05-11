package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public static CartDto from(Cart cart) {
        return CartDto.builder()
                .userId(cart.getId())
                .build();
    }
}
