package com.example.onlinestore.controller;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        Optional<ReviewDto> reviewDtoOptional = reviewService.searchReviewById(id);
        return reviewDtoOptional
                .map(reviewDto -> new ResponseEntity<>(reviewDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProduct(@PathVariable Long productId) {
        ProductDto product = new ProductDto(productId);
        List<ReviewDto> reviews = reviewService.searchReviewByProduct(product);
        return ResponseEntity.ok(reviews);
    }
}
