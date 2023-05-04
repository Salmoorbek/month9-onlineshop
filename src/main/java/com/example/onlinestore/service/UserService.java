package com.example.onlinestore.service;

import com.example.onlinestore.entity.User;
import com.example.onlinestore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(String name, String email, String password){
       var user = User.builder()
               .name(name)
               .email(email)
               .password(password)
               .build();
       return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAllUsers();
    }
}
