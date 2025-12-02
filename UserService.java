package com.example.lms.service;

import com.example.lms.dto.UserDto;
import com.example.lms.entity.User;
import com.example.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserDto userDto) {
        // Check if username already exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Create new user with hashed password
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Save and return the user
        return userRepository.save(user);
    }
}
