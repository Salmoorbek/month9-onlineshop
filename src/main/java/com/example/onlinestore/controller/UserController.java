package com.example.onlinestore.controller;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.exception.UserNotFoundException;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    @GetMapping("/")
    public String getIndexPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String userEmail = authentication.getName();
            model.addAttribute("userEmail", userEmail);
        }
        return "index";
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

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
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
