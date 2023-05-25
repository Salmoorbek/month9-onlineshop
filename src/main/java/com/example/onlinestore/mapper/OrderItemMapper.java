package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.dto.OrderItemDto;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public static OrderItemDto from(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}
