package com.example.onlinestore.service;

import com.example.onlinestore.dto.CartDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.CartMapper;
import com.example.onlinestore.repositories.CartItemRepository;
import com.example.onlinestore.repositories.CartRepository;
import com.example.onlinestore.repositories.ProductRepository;
import com.example.onlinestore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserService userService, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    public CartDto getCartByUserEmail(String email) {
        return CartMapper.from(Objects.requireNonNull(cartRepository.findCartByUser(email).orElse(null)));
    }

    public void removeItemFromCart(String userEmail, Long cartItemId) {
        Cart cart = cartRepository.findByUserEmail(userEmail);
        if (cart != null) {
            CartItem cartItemToRemove = cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getId().equals(cartItemId))
                    .findFirst()
                    .orElse(null);
            if (cartItemToRemove != null) {
                cart.getCartItems().remove(cartItemToRemove);
                cartItemRepository.delete(cartItemToRemove);
            }
        }
    }

    public void addItemToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = CartItem.builder()
                            .cart(cart)
                            .product(product)
                            .quantity(1)
                            .price(product.getPrice())
                            .build();
            cartItemRepository.save(newCartItem);
        }
    }

    public void updateCartItemQuantity(String userEmail, Long cartItemId, Integer quantity) {
        User user = userService.getUserByEmail(userEmail);

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();

            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("Cart item not found");
        }
    }
    public List<CartItem> getCartItemsForUser(User user) {
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart != null) {
            return cart.getCartItems();
        } else {
            return Collections.emptyList();
        }
    }

    public void clearCartForUser(User user) {
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if (cart != null) {
            List<CartItem> cartItems = cart.getCartItems();
            cartItems.clear();
            cartRepository.save(cart);
        }
    }
}

