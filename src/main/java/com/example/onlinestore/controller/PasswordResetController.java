package com.example.onlinestore.controller;

import com.example.onlinestore.dto.RecoverDto;
import com.example.onlinestore.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PasswordResetController {

    private final UserService userService;

    public PasswordResetController(UserService userService) {
        this.userService = userService;
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
