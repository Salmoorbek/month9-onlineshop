package com.example.onlinestore.controller;

import com.example.onlinestore.dto.*;
import com.example.onlinestore.exception.ProductNotFoundException;
import com.example.onlinestore.exception.ReviewNotFoundException;
import com.example.onlinestore.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public ReviewController(ReviewService reviewService, ProductService productService, OrderService orderService, OrderItemService orderItemService) {
        this.reviewService = reviewService;
        this.productService = productService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }
    @GetMapping("/reviews")
    public String getAllReviews(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = null;
        if (authentication.isAuthenticated()) {
            userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }

        List<OrderDto> userOrders = orderService.getOrderByUserEmail(userEmail);
        List<Long> purchasedProductIds = new ArrayList<>();

        for (OrderDto order : userOrders) {
            List<OrderItemDto> orderItems = orderItemService.getOrderItemsByOrderId(order.getId());
            for (OrderItemDto orderItem : orderItems) {
                purchasedProductIds.add(orderItem.getProductId());
            }
        }

        List<ProductDto> purchasedProducts = productService.getProductsByIds(purchasedProductIds);

        List<ReviewDto> reviews = new ArrayList<>();

        for (ReviewDto review : reviewService.getAllReview()) {
            if (purchasedProductIds.contains(review.getProductId())) {
                reviews.add(review);
            }
        }
        model.addAttribute("products", purchasedProducts);
        model.addAttribute("reviews", reviews);
        return "reviews";
    }




    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@Valid @PathVariable Long id) {
        Optional<ReviewDto> reviewDtoOptional = reviewService.searchReviewById(id);
        return reviewDtoOptional
                .map(reviewDto -> new ResponseEntity<>(reviewDto, HttpStatus.OK))
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));
    }
    @PostMapping("/reviews/create")
    public String addComment(@RequestParam("productId") Long productId,
                             @RequestParam("rating") Integer rating,
                             @RequestParam("comment") String comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(comment+ productId+ authentication.getName()+ rating);
        reviewService.addComments(comment, productId, authentication.getName(), rating);
        return "redirect:/reviews";
    }
}
