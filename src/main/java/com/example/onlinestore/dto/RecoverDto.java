package com.example.onlinestore.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RecoverDto {
    @Email(message = "Email is not valid")
    private String email;
}
