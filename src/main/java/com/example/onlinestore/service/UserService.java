package com.example.onlinestore.service;

import com.example.onlinestore.dto.UserDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(String name, String userName, String email, String password) {
        var user = User.builder()
                .name(name)
                .userName(userName)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto findUserByEmail(String email) {
        return UserMapper.fromPerson(userRepository.findByEmail(email));
    }
    public User findUserByEmailForOrder(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDto> searchUsersByName(String name) {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        for (User user : users) {
            UserDto userDto = new UserDto(user.getName(),user.getUserName(), user.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto searchUsersByUsername(String username) {
        return UserMapper.fromPerson(userRepository.findByUserNameContainingIgnoreCase(username));
    }

    public UserDto createUser(User user) {
        return UserMapper.fromPerson(userRepository.save(user));
    }

    public UserDto updateUser(User user) {
        return UserMapper.fromPerson(userRepository.save(user));
    }

    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
