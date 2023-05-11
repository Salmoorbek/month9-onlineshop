package com.example.onlinestore.controller;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllProducts(){
        return orderService.getAllProducts().stream()
                .map(OrderMapper::from)
                .collect(Collectors.toList());
    }
    @GetMapping("/{email}/orders")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable String email) {
        User userDto = userService.findUserByEmailForOrder(email);
        List<OrderDto> orders = orderService.getUserOrders(userDto);
        return ResponseEntity.ok(orders);
    }
}
