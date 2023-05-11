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
    public List<OrderDto> getUserOrders(User user) {
        List<Order> orders = orderRepository.findAllByUser(user);
        List<OrderDto> orderDtos = orders.stream().map(OrderMapper::from).collect(Collectors.toList());
        return orderDtos;
    }
}
