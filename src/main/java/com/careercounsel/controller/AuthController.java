package com.careercounsel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.careercounsel.model.User;
import com.careercounsel.repository.UserRepository;
import com.careercounsel.security.JwtUtil;
import com.careercounsel.service.EmailService; // ✅ Import the Email Service

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private EmailService emailService; // ✅ Inject the Email Service

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            Optional<User> existingUser = repo.findByUsername(user.getUsername());
            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Username already exists! Please log in."));
            }

            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("USER");
            }

            User savedUser = repo.save(user);

            // 🚨 NEW: Send Welcome Email 🚨
            // Assuming the 'username' is their email address (like ashutosh12@gmail.com)
            try {
                String subject = "Welcome to Career Counselling!";
                String body = "Hello,\n\nYour account has been successfully created. You can now log in to book your 1-on-1 counseling sessions, analyze your resume, and generate your career roadmap!\n\nBest regards,\nThe Career Counselling Team";
                emailService.sendConfirmationEmail(savedUser.getUsername(), subject, body);
            } catch (Exception e) {
                System.out.println("User registered, but email failed to send: " + e.getMessage());
            }

            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            var dbUser = repo.findByUsername(user.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found. Please sign up first."));

            if (!dbUser.getPassword().equals(user.getPassword())) {
                throw new RuntimeException("Invalid password.");
            }

            String token = jwt.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}