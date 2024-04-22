package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
