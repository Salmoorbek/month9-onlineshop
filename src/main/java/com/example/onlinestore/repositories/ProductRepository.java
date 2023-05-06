package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Product;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByNameContainingIgnoreCase(String name);
//    List<Product> findByCategory(Category category);
//    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
//    Page<Product> findByNameContainingIgnoreCaseOrderByName(String name, Pageable pageable);
//    Page<Product> findByPriceBetweenOrderByPrice(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
