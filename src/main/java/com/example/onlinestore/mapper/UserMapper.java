package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public static UserDto fromPerson(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
