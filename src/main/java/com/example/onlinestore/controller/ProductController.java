package com.example.onlinestore.controller;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts().stream()
                .map(ProductMapper::fromPerson)
                .collect(Collectors.toList());
    }
    @GetMapping("/{name}")
    public ResponseEntity<ProductDto> getProductsByName(@PathVariable String name){
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }
}
