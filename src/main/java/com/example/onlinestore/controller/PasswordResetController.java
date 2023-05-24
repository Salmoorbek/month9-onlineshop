package com.example.onlinestore.controller;

import com.example.onlinestore.entity.User;
import com.example.onlinestore.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private final UserRepository userRepository;

    public PasswordResetController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String sendResetToken(@RequestParam("email") String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String resetToken = generateResetToken();
            user.setResetToken(resetToken);
            userRepository.save(user);
            return "redirect:/forgot-password?success";
        } else {
            return "redirect:/forgot-password?error";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        User user = userRepository.findByResetToken(token);
        if (user != null) {
            model.addAttribute("token", token);
            return "reset-password";
        }
        return "redirect:/forgot-password?error";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        User user = userRepository.findByResetToken(token);
        if (user != null) {
            user.setPassword(newPassword);
            user.setResetToken(null);
            userRepository.save(user);
            return "redirect:/login?resetSuccess";
        }
        return "redirect:/forgot-password?error";
    }

    private String generateResetToken() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(UUID.randomUUID().toString());
    }
}
