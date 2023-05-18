package com.example.onlinestore.service;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.CartMapper;
import com.example.onlinestore.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public List<CartDto> getUserCarts(User user) {
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : cartRepository.findAllByUser(user)) {
            cartDtos.add(CartMapper.from(cart));
        }
        return cartDtos;
    }

    public List<CartDto> findShoppingCart(String email) {
        return cartRepository.findCartByUser(email).stream()
                .map(CartMapper::from)
                .collect(Collectors.toList());
    }
}
