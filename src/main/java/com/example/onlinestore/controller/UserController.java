package com.example.onlinestore.controller;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.repositories.UserRepository;
import com.example.onlinestore.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(UserMapper::fromPerson)
                .collect(Collectors.toList());
    }
}
