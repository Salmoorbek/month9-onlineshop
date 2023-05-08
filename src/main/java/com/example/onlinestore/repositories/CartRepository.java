package com.example.onlinestore.repositories;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
}
