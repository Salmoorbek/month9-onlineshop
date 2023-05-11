package com.example.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CategoryDto {
    @NotNull
    private Long title;
}
