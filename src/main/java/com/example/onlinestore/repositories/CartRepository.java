package com.example.onlinestore.repositories;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.Cart;
import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    @Query(value = "SELECT c FROM Cart AS c WHERE c.user.email = :email")
    Optional<Cart> findCartByUser(String email);
}
