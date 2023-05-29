package com.example.onlinestore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ResourceBundle;
@Configuration
public class AppConfiguration {
    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("messages");
    }
}
