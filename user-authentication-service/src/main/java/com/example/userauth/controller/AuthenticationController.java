
package com.example.userauth.controller;

import com.example.userauth.dto.UserDTO;
import com.example.userauth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String token = authenticationService.authenticateUser(userDTO);
        return ResponseEntity.ok("Bearer " + token);
    }
}
