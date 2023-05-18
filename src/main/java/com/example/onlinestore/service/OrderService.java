package com.example.onlinestore.service;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllProducts(){
        return orderRepository.findAll();
    }
    public List<OrderDto> getUserOrders(String email) {
        return orderRepository.findUserOrders(email).stream()
                .map(OrderMapper::from)
                .collect(Collectors.toList());
    }
}
