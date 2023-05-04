package com.example.onlinestore.service;

import com.example.onlinestore.entity.Order;
import com.example.onlinestore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllProducts(){
        return orderRepository.findAll();
    }
}
