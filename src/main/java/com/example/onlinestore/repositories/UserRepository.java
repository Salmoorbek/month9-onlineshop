package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
