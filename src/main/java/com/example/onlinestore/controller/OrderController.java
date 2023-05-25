package com.example.onlinestore.controller;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.dto.OrderItemDto;
import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.CartItem;
import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService, OrderItemService orderItemService, ProductService productService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.productService = productService;
    }
    @GetMapping("/orders")
    public String getAllUserOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }

        List<OrderDto> userOrders = orderService.getOrderByUserEmail(userEmail);

        model.addAttribute("userOrders", userOrders);
        return "orders";
    }

    @GetMapping("/orders/{orderId}/items")
    public String getOrderItems(@PathVariable Long orderId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }

        Order order = orderService.getOrderById(orderId);

        if (order != null && order.getUser().getEmail().equals(userEmail)) {
            List<OrderItemDto> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("order", order);
            List<ProductDto> products = new ArrayList<>();
            for (OrderItemDto cartItem : orderItems) {
                ProductDto product = productService.getProductDtoById(cartItem.getProductId());
                products.add(product);
            }
            model.addAttribute("products", products);
            return "order_items";
        } else {
            return "orders";
        }
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
