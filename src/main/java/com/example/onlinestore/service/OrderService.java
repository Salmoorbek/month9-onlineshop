package com.example.onlinestore.service;

import com.example.onlinestore.dto.OrderDto;
import com.example.onlinestore.entity.*;
import com.example.onlinestore.mapper.CartMapper;
import com.example.onlinestore.mapper.OrderMapper;
import com.example.onlinestore.repositories.OrderItemRepository;
import com.example.onlinestore.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrder(User user, List<CartItem> cartItems) {
        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            total = total.add(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            orderItems.add(orderItem);
        }

        order.setTotal(total);

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        return order;
    }

    public List<OrderDto> getOrderByUserEmail(String email) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (int i = 0; i < orderRepository.getOrdersByUserEmail(email).size(); i++) {
            orderDtos.add(OrderMapper.from(orderRepository.getOrdersByUserEmail(email).get(i)));
        }
        return orderDtos;
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }
}

