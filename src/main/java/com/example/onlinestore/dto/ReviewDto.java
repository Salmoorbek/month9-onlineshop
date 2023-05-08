package com.example.onlinestore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String text;
    private Integer rating;
    private LocalDateTime createdAt;
}
