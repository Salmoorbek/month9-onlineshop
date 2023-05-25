package com.example.onlinestore.service;

import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.dto.OrderItemDto;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.OrderItem;
import com.example.onlinestore.mapper.CartItemMapper;
import com.example.onlinestore.mapper.OrderItemMapper;
import com.example.onlinestore.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemDto> getOrderItemsByOrderId(Long cartId) {
        List<OrderItem> cartItems = orderItemRepository.findOrderItemsByOrderId(cartId);
        List<OrderItemDto> cartItemDtos = new ArrayList<>();
        for (OrderItem cartItem : cartItems) {
            cartItemDtos.add(OrderItemMapper.from(cartItem));
        }
        return cartItemDtos;
    }
}
