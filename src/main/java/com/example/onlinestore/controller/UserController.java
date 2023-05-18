package com.example.onlinestore.controller;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.exception.UserAlreadyExistsException;
import com.example.onlinestore.exception.UserNotFoundException;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }
    @GetMapping("/register")
    public String getRegistrationPage(Model model){
        model.addAttribute("dto", new UserRegisterDto());
        return "register";
    }
    @PostMapping(value = "/register")
    public String register(@Valid UserRegisterDto user,
                           BindingResult validationResult,
                           RedirectAttributes attributes){

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping(value = "/api/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::from)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/users/name/{name}")
    public ResponseEntity<List<UserDto>> searchUsersByName(
            @Valid @PathVariable String name) {
        if (name != null) {
            return ResponseEntity.ok(userService.findByName(name));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/api/users/email/{email}")
    public ResponseEntity<UserDto> searchUserByUserEmail(
            @Valid @PathVariable String email) {
        if (email != null) {
            return ResponseEntity.ok(userService.searchUsersByUsername(email));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegisterDto> updateUser(@Valid @PathVariable Long id, @RequestBody UserRegisterDto user) {
        Optional<UserDto> existingUser = userService.findUserById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            UserRegisterDto updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }
    @GetMapping("/check/{email}")
    public ResponseEntity<String> checkUserExistsByEmail(@Valid @PathVariable String email) {
        boolean exists = userService.isUserExistsByEmail(email);
        if (exists) {
            return ResponseEntity.ok("User with email " + email + " exists");
        } else {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
    }
}
