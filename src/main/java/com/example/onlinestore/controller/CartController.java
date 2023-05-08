package com.example.onlinestore.controller;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.CartService;
import com.example.onlinestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }
    @GetMapping("/{email}/carts")
    public ResponseEntity<List<CartDto>> getUserCarts(@PathVariable String email) {
        User user = userService.findUserByEmailForOrder(email);
        List<CartDto> carts = cartService.getUserCarts(user);
        return ResponseEntity.ok(carts);
    }
}
