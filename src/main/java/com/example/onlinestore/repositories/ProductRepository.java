package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Category;
import com.example.onlinestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByNameAsc(Pageable pageable);

    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

    Page<Product> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

    Page<Product> findAllByPriceLessThanEqualOrderByPriceAsc(BigDecimal price, Pageable pageable);

    Page<Product> findAllByNameContainingIgnoreCaseAndCategoryIdOrderByNameAsc(String name, Long category, Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);
}