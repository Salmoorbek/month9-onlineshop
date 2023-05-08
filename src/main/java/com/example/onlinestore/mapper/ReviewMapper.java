package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.ReviewDto;
import com.example.onlinestore.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public static ReviewDto fromPerson(Review review) {
        return ReviewDto.builder()
                .userId(review.getUser().getId())
                .productId(review.getProduct().getId())
                .text(review.getText())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
