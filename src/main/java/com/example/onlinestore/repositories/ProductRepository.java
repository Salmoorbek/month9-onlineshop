package com.example.onlinestore.repositories;

import com.example.onlinestore.entity.Category;
import com.example.onlinestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByNameAsc(Pageable pageable);

    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);

    Page<Product> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

    Page<Product> findAllByPriceLessThanEqualOrderByPriceAsc(BigDecimal price, Pageable pageable);

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE "
            + "(:name IS NULL OR p.name LIKE %:name%) AND "
            + "(:description IS NULL OR p.description LIKE %:description%) AND "
            + "(:price IS NULL OR p.price = :price) AND "
            + "(:categoryId IS NULL OR p.category.id = :categoryId)")
    Page<Product> findBySearchParams(
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") BigDecimal price,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

}