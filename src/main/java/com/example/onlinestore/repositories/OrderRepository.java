package com.example.onlinestore.repositories;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.Order;
import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order AS o WHERE o.user.email = :email")
    List<Order> findUserOrders(String email);
}
