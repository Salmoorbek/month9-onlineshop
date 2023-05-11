package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Category;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.repositories.CategoryRepository;
import com.example.onlinestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAllByOrderByNameAsc(pageable);
        return products.map(ProductMapper::from);
    }

    public Page<ProductDto> getProductsSortedByPrice(Pageable pageable) {
        Page<Product> products = productRepository.findAllByOrderByPriceAsc(pageable);
        return products.map(ProductMapper::from);
    }

    public Page<ProductDto> getProductsByCategory(Long categoryId, Pageable pageable) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Category with id " + categoryId + " not found");
        }
        Category category = optionalCategory.get();
        Page<Product> productsPage = productRepository.findByCategory(category, pageable);
        return productsPage.map(ProductMapper::from);
    }

    public Page<ProductDto> searchProductsByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(name, pageable);
        return products.map(ProductMapper::from);
    }

    public Page<ProductDto> searchProductsByPrice(BigDecimal price, Pageable pageable) {
        Page<Product> products = productRepository.findAllByPriceLessThanEqualOrderByPriceAsc(price, pageable);
        return products.map(ProductMapper::from);
    }

    public Page<ProductDto> searchProductsByNameAndCategory(String name, Long category, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContainingIgnoreCaseAndCategoryIdOrderByNameAsc(name, category, pageable);
        return products.map(ProductMapper::from);
    }
}
