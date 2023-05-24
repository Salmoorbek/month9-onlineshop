package com.example.onlinestore.controller;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getAllProducts(Model model, @Valid @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getAllProducts(pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }

    @GetMapping("/product/sorted")
    public String getProductsSortedByPrice(Model model, @Valid @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getProductsSortedByPrice(pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }

    @GetMapping("/product/name")
    public String searchProductsByName(Model model, @Valid @RequestParam String name,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByName(name, pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }

    @GetMapping("/product/price")
    public String searchProductsByPrice(Model model, @Valid @RequestParam BigDecimal minPrice,
                                        @RequestParam BigDecimal maxPrice,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByPrice(minPrice, maxPrice, pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }

    @GetMapping("/product/description")
    public String searchProductsByDescription(Model model, @Valid @RequestParam String description,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.searchProductsByDescription(description, pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }

    @GetMapping("/product/category")
    public String getProductsByCategory(Model model, @Valid @RequestParam(required = false) Long category,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDto> products = productService.getProductsByCategory(category, pageable);
        model.addAttribute("products", products);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "products";
    }
}
