package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public static CartDto fromPerson(Cart cart) {
        return CartDto.builder()
                .userId(cart.getId())
                .build();
    }
}
