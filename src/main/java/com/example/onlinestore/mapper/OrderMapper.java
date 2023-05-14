package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .userId(order.getUser().getId())
                .createdAt(order.getCreatedAt())
                .status(order.getStatus().name())
                .total(order.getTotal())
                .build();
    }
}
