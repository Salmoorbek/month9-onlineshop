package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Review;
import com.example.onlinestore.mapper.ReviewMapper;
import com.example.onlinestore.repositories.ProductRepository;
import com.example.onlinestore.repositories.ReviewRepository;
import com.example.onlinestore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Optional<ReviewDto> searchReviewById(Long id){
        return Optional.ofNullable(ReviewMapper.from(Objects.requireNonNull(reviewRepository.findById(id).orElse(null))));
    }
    public List<ReviewDto> searchReviewByProduct(ProductDto product){
        Product product1 = new Product(product.getId());
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (int i = 0; i < reviewRepository.findByProduct(product1).size(); i++) {
            reviewDtos.add(ReviewMapper.from(reviewRepository.findByProduct(product1).get(i)));
        }
        return reviewDtos;
    }
    public ReviewDto saveReview(ReviewDto reviewDto) {
        var user = userRepository.findById(reviewDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        var product = productRepository.findById(reviewDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        var review = Review.builder()
                .user(user)
                .product(product)
                .text(reviewDto.getText())
                .rating(reviewDto.getRating())
                .createdAt(reviewDto.getCreatedAt())
                .build();

        var savedReview = reviewRepository.save(review);
        return ReviewMapper.from(savedReview);
    }
}
