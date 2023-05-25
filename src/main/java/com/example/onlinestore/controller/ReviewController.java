package com.example.onlinestore.controller;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.exception.ProductNotFoundException;
import com.example.onlinestore.exception.ReviewNotFoundException;
import com.example.onlinestore.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    public ResponseEntity<ReviewDto> getReviewById(@Valid @PathVariable Long id) {
        Optional<ReviewDto> reviewDtoOptional = reviewService.searchReviewById(id);
        return reviewDtoOptional
                .map(reviewDto -> new ResponseEntity<>(reviewDto, HttpStatus.OK))
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProduct(@Valid @PathVariable Long productId) {
        ProductDto product = new ProductDto(productId);
        List<ReviewDto> reviews = reviewService.searchReviewByProduct(product);
        if (reviews.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return ResponseEntity.ok(reviews);
    }
    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto createdReview = reviewService.saveReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PostMapping("/comments")
    public String addComments(@RequestParam(name="comments") String comments, @RequestParam(name="clothes_id") Long clothesId, Principal principal){
        System.out.println(clothesId);
        reviewService.addComments(comments,clothesId,principal.getName());
        return "redirect:/order";
    }
}
