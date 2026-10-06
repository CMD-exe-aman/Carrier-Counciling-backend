package com.careercounsel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.careercounsel.model.Booking;
import com.careercounsel.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public Booking save(Booking booking) {
        return repo.save(booking);
    }
}