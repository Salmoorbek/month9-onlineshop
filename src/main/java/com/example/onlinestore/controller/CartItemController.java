package com.example.onlinestore.controller;

import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.repositories.CartItemRepository;
import com.example.onlinestore.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart_item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping("/cart-items/{cartId}")
    public ResponseEntity<List<CartItemDto>> getCartItemByCartId(@Valid @PathVariable Long cartId) {
        List<CartItemDto> cartItemDtos = cartItemService.getCartItemByCartId(cartId);
        if (!cartItemDtos.isEmpty()) {
            return ResponseEntity.ok(cartItemDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
