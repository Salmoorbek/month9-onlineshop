package com.example.onlinestore.service;

import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.CartItemMapper;
import com.example.onlinestore.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItemDto> getCartItemByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.selectCartItemsByCartId(cartId);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemDtos.add(CartItemMapper.from(cartItem));
        }
        return cartItemDtos;
    }
}
