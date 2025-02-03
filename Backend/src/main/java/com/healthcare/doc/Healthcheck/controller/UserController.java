package com.healthcare.doc.Healthcheck.controller;

import com.healthcare.doc.Healthcheck.config.JwtUtil;
import com.healthcare.doc.Healthcheck.model.User;
import com.healthcare.doc.Healthcheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // DTO for login request
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // User Registration endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role specified!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // User Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            // Authenticate user based on their username and password
            User authenticatedUser = userService.authenticateUser(username, password);

            if (authenticatedUser == null) {
                return ResponseEntity.badRequest().body("Invalid username or password!");
            }

            // Generate JWT token with the user role
            String token = jwtUtil.generateToken(authenticatedUser.getUsername(), authenticatedUser.getRole());

            // Return token and role in the response
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", authenticatedUser.getRole()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserProfile(@RequestBody Object userProfile,Authentication authentication) {
        String username = authentication.getName(); // Get username from the authenticated user
        userService.updateUserProfile(username, userProfile);
        return ResponseEntity.ok("User profile updated successfully!");
    }
}
