package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAllByOrderByNameAsc(pageable);
        return products.map(ProductMapper::fromPerson);
    }

    public Page<ProductDto> getProductsSortedByPrice(Pageable pageable) {
        Page<Product> products = productRepository.findAllByOrderByPriceAsc(pageable);
        return products.map(ProductMapper::fromPerson);
    }

    public Page<ProductDto> getProductsByCategory(String category, Pageable pageable) {
        Page<Product> products = productRepository.findAllByCategoryOrderByPriceAsc(category, pageable);
        return products.map(ProductMapper::fromPerson);
    }

    public Page<ProductDto> searchProductsByName(String name, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(name, pageable);
        return products.map(ProductMapper::fromPerson);
    }

    public Page<ProductDto> searchProductsByPrice(BigDecimal price, Pageable pageable) {
        Page<Product> products = productRepository.findAllByPriceLessThanEqualOrderByPriceAsc(price, pageable);
        return products.map(ProductMapper::fromPerson);
    }

    public Page<ProductDto> searchProductsByNameAndCategory(String name, String category, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContainingIgnoreCaseAndCategoryOrderByNameAsc(name, category, pageable);
        return products.map(ProductMapper::fromPerson);
    }
}
