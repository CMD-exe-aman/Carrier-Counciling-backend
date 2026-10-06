package com.careercounsel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    // Ensures only logged-in users (not guests) can access this
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getUserDashboard() {
        // Extract the username from the validated JWT Token
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // In the future, you will inject BookingRepository here to fetch their specific bookings
        // e.g., List<Booking> myBookings = bookingRepo.findByUsername(username);

        Map<String, Object> userData = Map.of(
                "username", username,
                "status", "Active",
                "message", "Welcome to your Career Portal!",
                "recentActivity", List.of("Created Account", "Logged In")
        );

        return ResponseEntity.ok(userData);
    }
}