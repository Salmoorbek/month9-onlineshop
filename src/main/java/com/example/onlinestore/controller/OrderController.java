package com.example.onlinestore.controller;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.exception.UserNotFoundException;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAllProducts(){
        return orderService.getAllProducts().stream()
                .map(OrderMapper::from)
                .collect(Collectors.toList());
    }
    @GetMapping("/api/user_orders")
    public ResponseEntity<List<OrderDto>> findUserOrders(Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(orderService.getUserOrders(email),HttpStatus.OK);
    }
}
