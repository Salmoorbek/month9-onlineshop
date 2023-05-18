package com.example.onlinestore.controller;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.service.CartService;
import org.springframework.http.HttpStatus;
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

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/{email}/carts")
    public ResponseEntity<List<CartDto>> getUserCarts(@PathVariable String email) {
        return new ResponseEntity<>(cartService.findShoppingCart(email), HttpStatus.OK);
    }
}
