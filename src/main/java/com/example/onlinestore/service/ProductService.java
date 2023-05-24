package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Category;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.repositories.CategoryRepository;
import com.example.onlinestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Page<ProductDto> searchProductsByPrice(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<Product> productPage = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        List<ProductDto> productDtos = productPage.getContent()
                .stream()
                .map(ProductMapper::from)
                .collect(Collectors.toList());

        return new PageImpl<>(productDtos, pageable, productPage.getTotalElements());
    }

    public Page<ProductDto> searchProductsByDescription(String description, Pageable pageable) {
        Page<Product> products = productRepository.findByDescriptionContainingIgnoreCase(description, pageable);
        return products.map(ProductMapper::from);
    }

    public ProductDto getProductDtoById(Long productId) {
        return ProductMapper.from(productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + productId)));
    }
}
