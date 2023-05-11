package com.example.onlinestore.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @URL
    private String imageUrl;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    public ProductDto(Long id) {
        this.id = id;
    }
}
