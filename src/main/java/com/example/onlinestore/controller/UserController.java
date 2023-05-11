package com.example.onlinestore.controller;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/search_by_name")
    public ResponseEntity<List<UserDto>> searchUsersByName(
            @RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(userService.searchUsersByName(name));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search_by_user_name")
    public ResponseEntity<UserDto> searchUserByUserName(
            @RequestParam(required = false) String username) {
        if (username != null) {
            return ResponseEntity.ok(userService.searchUsersByUsername(username));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search_by_email")
    public ResponseEntity<UserDto> searchUserByUserEmail(
            @RequestParam(required = false) String email) {
        if (email != null) {
            return ResponseEntity.ok(userService.searchUsersByUsername(email));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserRegisterDto> createUser(@Valid @RequestBody UserRegisterDto user) {
        if (userService.isUserExistsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            UserRegisterDto savedUser = userService.createUser(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
                    .buildAndExpand(savedUser.getEmail()).toUri();
            return ResponseEntity.created(location).body(savedUser);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterDto> updateUser(@PathVariable Long id, @RequestBody UserRegisterDto user) {
        Optional<UserDto> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            UserRegisterDto updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/check/{email}")
    public ResponseEntity<String> checkUserExistsByEmail(@PathVariable String email) {
        boolean exists = userService.isUserExistsByEmail(email);
        if (exists) {
            return ResponseEntity.ok("User with email " + email + " exists");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
