package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Object> findByTitle(String categoryTitle);
}
