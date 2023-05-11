package com.example.onlinestore.controller;

import com.example.onlinestore.exception.CartItemNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartItemControllerAdvice {
    @ExceptionHandler({CartItemNotFoundException.class, DataAccessException.class})
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        HttpStatus status = ex instanceof CartItemNotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage() != null ? ex.getMessage() : "Something went wrong!";
        return ResponseEntity.status(status).body(message);
    }
}
