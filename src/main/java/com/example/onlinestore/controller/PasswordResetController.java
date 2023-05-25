package com.example.onlinestore.controller;

import com.example.onlinestore.dto.NewPasswordDto;
import com.example.onlinestore.dto.RecoverDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.repositories.UserRepository;
import com.example.onlinestore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordResetController {

    private final UserService userService;

    public PasswordResetController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/password/change")
    public ResponseEntity<String> changePassword(@Valid @RequestBody NewPasswordDto changePasswordDTO,
                                                 Principal principal,
                                                 Model model,
                                                 BindingResult validationResult){
        if (validationResult.hasFieldErrors()) {
            model.addAttribute("errors", validationResult.getFieldErrors());
        }

        String email = principal.getName();
        String response = userService.changePassword(changePasswordDTO, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reset-password")
    public String getRecoverPage(Model model){
        model.addAttribute("dto", new RecoverDto());
        return "reset-password";
    }

    @PostMapping(value = "/user/password/recover")
    public String recoverPassword(@Valid RecoverDto recoverPasswordDTO,
                                  BindingResult validationResult,
                                  RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/reset-password";
        }

        try {
            String password = userService.recoverPassword(recoverPasswordDTO);
            attributes.addFlashAttribute("password", password);
            return "redirect:/login";
        } catch (UsernameNotFoundException e) {
            attributes.addFlashAttribute("customerError", e.getMessage());
            return "redirect:/reset-password";
        }
    }
}
