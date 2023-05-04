package com.example.onlinestore.dto;

import lombok.*;

import javax.persistence.Column;

@Builder
@Data
public class ProductDto {
    private String name;
    private String image;
    private String description;
    private int quantity;
    private Double price;
}
