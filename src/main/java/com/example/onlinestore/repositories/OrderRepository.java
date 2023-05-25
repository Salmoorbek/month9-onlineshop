package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order AS o WHERE o.user.email = :email")
    List<Order> getOrdersByUserEmail(String email);
}
