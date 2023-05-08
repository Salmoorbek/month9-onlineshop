package com.example.onlinestore.service;

import com.example.onlinestore.dto.UserDto;

import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.ReviewMapper;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.mapper.UserRegisterMapper;
import com.example.onlinestore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto findUserByEmail(String email) {
        return UserMapper.fromPerson(userRepository.findByEmail(email));
    }
    public Optional<UserDto> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::fromPerson);
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

    public UserRegisterDto createUser(UserRegisterDto user) {
        var usr = User.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        return UserRegisterMapper.fromPerson(userRepository.save(usr));
    }

    public UserRegisterDto updateUser(UserRegisterDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);
        return UserRegisterMapper.fromPerson(updatedUser);
    }

    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
