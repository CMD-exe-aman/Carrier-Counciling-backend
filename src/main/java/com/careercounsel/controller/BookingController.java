package com.careercounsel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.careercounsel.model.Booking;
import com.careercounsel.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return service.save(booking);
    }
}