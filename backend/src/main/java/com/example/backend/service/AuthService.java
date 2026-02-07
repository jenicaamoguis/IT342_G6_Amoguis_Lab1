package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new user
    public String register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    // Login user
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return "User not found!";
        if (!passwordEncoder.matches(password, user.getPassword())) return "Invalid password!";
        return "Login successful!";
    }

    // Get user info
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
