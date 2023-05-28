package com.example.onlinestore.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    @NotBlank
    @Size(max = 1000)
    private String text;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    @NotNull
    private LocalDateTime createdAt;
}
