package com.example.onlinestore.controller;

import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.CartService;
import com.example.onlinestore.service.OrderService;
import com.example.onlinestore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping("/orders/create")
    public String createOrder(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        List<CartItem> cartItems = cartService.getCartItemsForUser(user);

        Order order = orderService.createOrder(user, cartItems);

        cartService.clearCartForUser(user);

        return "redirect:/orders/" + order.getId();
    }
    @GetMapping("/orders/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "carts";
    }
}
