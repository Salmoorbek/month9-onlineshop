package com.example.onlinestore.controller;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.exception.UserNotFoundException;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<OrderDto>> getUserOrders(@Valid  @PathVariable String email) {
        User userDto;
        try {
            userDto = userService.findUserByEmailForOrder(email);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("User not found");
        }
        List<OrderDto> orders = orderService.getUserOrders(userDto);
        return ResponseEntity.ok(orders);
    }
}
