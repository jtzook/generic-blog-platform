package com.jtzook.gbapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.jtzook.gbapi.models.User;
import com.jtzook.gbapi.requests.LoginRequest;
import com.jtzook.gbapi.requests.RegistrationRequest;
import com.jtzook.gbapi.responses.ApiResponse;
import com.jtzook.gbapi.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) {
        try {
            userService.registerUser(registrationRequest.getUsername(), registrationRequest.getPassword(), registrationRequest.getEmail());
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.getUserByUsername(loginRequest.getUsername());
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            // Implement login logic here, e.g., comparing passwords

            return ResponseEntity.ok(new ApiResponse(true, "Login successful"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
