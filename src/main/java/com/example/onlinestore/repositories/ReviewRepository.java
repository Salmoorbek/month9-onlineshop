package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct(Product product);
}
