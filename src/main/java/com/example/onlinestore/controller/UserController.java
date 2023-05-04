package com.example.onlinestore.controller;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping("/register")
    public UserDto addPerson(@RequestBody User user) {
        User addedUser  = userService.saveUser(user.getName(), user.getEmail(), user.getPassword());
        return userMapper.fromPerson(addedUser);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(UserMapper::fromPerson)
                .collect(Collectors.toList());
    }
}
