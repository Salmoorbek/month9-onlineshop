package com.example.onlinestore.service;

import com.example.onlinestore.dto.ProductDto;
import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.ReviewMapper;
import com.example.onlinestore.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Optional<ReviewDto> searchReviewById(Long id){
        return Optional.ofNullable(ReviewMapper.fromPerson(Objects.requireNonNull(reviewRepository.findById(id).orElse(null))));
    }
    public List<ReviewDto> searchReviewByProduct(ProductDto product){
        Product product1 = new Product(product.getId());
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (int i = 0; i < reviewRepository.findByProduct(product1).size(); i++) {
            reviewDtos.add(ReviewMapper.fromPerson(reviewRepository.findByProduct(product1).get(i)));
        }
        return reviewDtos;
    }
}
