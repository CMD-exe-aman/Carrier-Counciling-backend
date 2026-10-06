package com.careercounsel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.careercounsel.repository.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private ContactRepository contactRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private UserRepository userRepo; // ✅ Added User Repository

    // 1. Get all stats (Now includes Users)
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return Map.of(
                "contacts", contactRepo.findAll(),
                "bookings", bookingRepo.findAll(),
                "users", userRepo.findAll(), // ✅ Send user list to frontend
                "totalContacts", contactRepo.count(),
                "totalBookings", bookingRepo.count(),
                "totalUsers", userRepo.count() // ✅ Send total user count
        );
    }

    // 2. Delete a Booking by ID
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        if (bookingRepo.existsById(id)) {
            bookingRepo.deleteById(id);
            return ResponseEntity.ok().body(Map.of("message", "Booking deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }

    // 3. Delete a Contact Message by ID
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        if (contactRepo.existsById(id)) {
            contactRepo.deleteById(id);
            return ResponseEntity.ok().body(Map.of("message", "Contact deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }

    // 4. Delete a User by ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok().body(Map.of("message", "User deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }
}