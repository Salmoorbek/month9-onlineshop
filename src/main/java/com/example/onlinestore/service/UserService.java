package com.example.onlinestore.service;

import com.example.onlinestore.dto.NewPasswordDto;
import com.example.onlinestore.dto.RecoverDto;
import com.example.onlinestore.dto.UserDto;

import com.example.onlinestore.dto.UserRegisterDto;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.exception.UserAlreadyExistsException;
import com.example.onlinestore.mapper.UserMapper;
import com.example.onlinestore.mapper.UserRegisterMapper;
import com.example.onlinestore.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDto findUserByEmail(String email) {
        return UserMapper.from(userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found")));
    }
    public Optional<UserDto> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::from);
    }


    public UserDto searchUsersByUsername(String username) {
        return UserMapper.from(userRepository.findByUsernameContainingIgnoreCase(username));
    }

    public void createUser(UserRegisterDto user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException("The client with this email is already registered");
        }

        var usr = User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role("user")
                .enabled(true)
                .build();

        UserRegisterMapper.from(userRepository.save(usr));
    }

    public UserRegisterDto updateUser(UserRegisterDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);
        return UserRegisterMapper.from(updatedUser);
    }

    public boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    }

    public List<UserDto> findByName(String username){
        List<User> users = userRepository.findByUsernameIsContainingIgnoreCase(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return users.stream()
                .map(UserMapper::from)
                .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        return user;
    }

    public String changePassword(NewPasswordDto changePasswordDTO, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Not found"));

        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
            return "Failed to change password. The current password is entered incorrectly";
        }

        if (changePasswordDTO.getCurrentPassword().equals(changePasswordDTO.getNewPassword())) {
            return "Failed to change password. The current password and the new password are the same.";
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return "The password has been changed successfully.";
    }

    public String recoverPassword(RecoverDto recoverPasswordDTO) {
        User user = userRepository.findByEmail(recoverPasswordDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        int passwordLength = 10;

        String randomPassword = RandomStringUtils.randomAlphabetic(passwordLength).toLowerCase();
        user.setPassword(passwordEncoder.encode(randomPassword));
        userRepository.save(user);

        return randomPassword;
    }
}
