package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
//    User findByUsername(String username);
    boolean existsByEmail(String email);
}
