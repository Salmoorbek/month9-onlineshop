package com.example.onlinestore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_url")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private Double price;
}
