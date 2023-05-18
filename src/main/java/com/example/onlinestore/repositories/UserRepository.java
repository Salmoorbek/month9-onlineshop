package com.example.onlinestore.repositories;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContainingIgnoreCase(String name);
    User findByUsernameContainingIgnoreCase(String username);
    boolean existsByEmail(String email);
    List<User> findByUsernameIsContainingIgnoreCase(String username);
}
