package com.example.onlinestore.service;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.dto.CartItemDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.CartMapper;
import com.example.onlinestore.repositories.CartItemRepository;
import com.example.onlinestore.repositories.CartRepository;
import com.example.onlinestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public List<CartDto> getUserCarts(User user) {
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : cartRepository.findAllByUser(user)) {
            cartDtos.add(CartMapper.from(cart));
        }
        return cartDtos;
    }

    public List<CartDto> findCart(String email) {
        return cartRepository.findCartByUser(email).stream()
                .map(CartMapper::from)
                .collect(Collectors.toList());
    }

    public boolean addToCart(CartItemDto cartItemDto, String email) {
        Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Cart cart = cartRepository.findCartByUser(email)
                .orElseThrow(() -> new IllegalArgumentException("User's shopping cart not found"));

        boolean isProductInCart = cartItemRepository.existsByCartAndProduct(cart, product);

        if (isProductInCart) {
            return false;
        }

        CartItem cartItem = CartItem.builder()
                .product(product)
                .cart(cart)
                .quantity(cartItemDto.getQuantity())
                .price(cartItemDto.getPrice())
                .build();

        cartItemRepository.save(cartItem);
        return true;
    }

    public boolean deleteFromCart(CartItemDto itemDTO, String email) {
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Cart cart = cartRepository.findCartByUser(email)
                .orElseThrow(() -> new IllegalArgumentException("User's shopping cart not found"));

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new IllegalArgumentException("Item not found in the shopping cart"));

        cartItemRepository.delete(cartItem);
        return true;
    }
}
