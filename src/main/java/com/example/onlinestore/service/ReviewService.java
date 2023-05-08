package com.example.onlinestore.service;

import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.mapper.ReviewMapper;
import com.example.onlinestore.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

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
}
