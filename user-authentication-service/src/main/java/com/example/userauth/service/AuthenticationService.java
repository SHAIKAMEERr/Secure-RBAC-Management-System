
package com.example.userauth.service;

import com.example.userauth.dto.UserDTO;
import com.example.userauth.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserDAO userDAO;

    public String authenticateUser(UserDTO userDTO) {
        // Logic for authenticating user and generating token
        if (userDAO.verifyUser(userDTO)) {
            return "sample-jwt-token"; // Replace with actual JWT generation logic
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
