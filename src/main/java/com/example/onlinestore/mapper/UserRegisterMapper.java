package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {
    public static UserRegisterDto from(User user) {
        return UserRegisterDto.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
