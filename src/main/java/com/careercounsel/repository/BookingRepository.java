package com.careercounsel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.careercounsel.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}