package com.example.onlinestore.dto;

import com.example.onlinestore.entity.User;
import lombok.*;

import javax.persistence.Column;
@Data
@Builder
public class UserDto {
    private String name;
    private String email;
}
