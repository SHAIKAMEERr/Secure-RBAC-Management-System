package com.example.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.model.UserDTO;
import com.example.authentication.model.UserEntity;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.security.JwtTokenProvider;
import com.example.userauth.exception.UserNotFoundException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Method to register a new user
    public void registerUser(UserDTO userDTO) {
        // Check if the user already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("User already exists with username: " + userDTO.getUsername());
        }
        
        // Encrypt the password before saving the user
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        // Save the user to the database
        UserEntity user = userDTO.toEntity();  // Convert DTO to Entity
        userRepository.save(user);
    }
    
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found", "USER_NOT_FOUND"));
    }

    // Method to encode a password (if required for some other usage)
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Method to log in the user
    public String loginUser(UserDTO userDTO) {
        // Find user by username
    	UserEntity user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if password matches
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT Token if authentication is successful
        return jwtTokenProvider.createToken(user.getUsername(), user.getRole());
    }
}
