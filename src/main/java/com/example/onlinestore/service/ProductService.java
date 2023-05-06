package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
//    public ProductDto getProductByName(String name){
//        return ProductDto.from(productRepository.findProductByName(name));
//    }
}
