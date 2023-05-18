package com.example.onlinestore.controller;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/api/carts/{email}")
    public ResponseEntity<List<CartDto>> getUserCarts(@Valid @PathVariable String email) {
        return new ResponseEntity<>(cartService.findCart(email), HttpStatus.OK);
    }

    @PostMapping("/api/carts/items/add")
    public ResponseEntity<Boolean> addItemToCart(@Valid @RequestBody CartItemDto cartItemDto, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(cartService.addToCart(cartItemDto, email),HttpStatus.OK);
    }

    @PostMapping("/api/carts/items/delete")
    public ResponseEntity<Boolean> deleteItemFromCart(@Valid @RequestBody CartItemDto cartItemDto, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(cartService.deleteFromCart(cartItemDto, email),HttpStatus.OK);
    }
}
