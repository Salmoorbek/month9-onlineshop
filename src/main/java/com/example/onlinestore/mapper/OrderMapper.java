package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static OrderDto fromPerson(Order order) {
        return OrderDto.builder()
                .userId(order.getUserId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .build();
    }
}
