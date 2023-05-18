package com.example.onlinestore.controller;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDto>> getAllProducts(@Valid @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/sorted")
    public ResponseEntity<Page<ProductDto>> getProductsSortedByPrice(@Valid @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getProductsSortedByPrice(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ProductDto>> searchProductsByName(@Valid @PathVariable String name,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByName(name, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price")
    public ResponseEntity<Page<ProductDto>> searchProductsByPrice( @Valid@RequestParam BigDecimal price,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByPrice(price, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/description")
    public ResponseEntity<Page<ProductDto>> searchProductsByDescription(@Valid @RequestParam String description,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByDescription(description, pageable);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/category")
    public ResponseEntity<Page<ProductDto>> getProductsByCategory(@Valid @RequestParam(required = false) Long category,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getProductsByCategory(category, pageable);
        return ResponseEntity.ok(products);
    }

}
