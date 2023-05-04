package com.example.onlinestore.controller;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllProducts(){
        return orderService.getAllProducts().stream()
                .map(OrderMapper::fromPerson)
                .collect(Collectors.toList());
    }
}
