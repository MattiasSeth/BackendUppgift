package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BookingDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Entities.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookingService {
    public BookingDto bookingToBookingDto(Booking b);

    public DetailedBookingDto bookingToDetailedBookingDto(Booking b);

    public Booking bookingToDetailedBookingDto(DetailedBookingDto b);

    public List<DetailedBookingDto> getAllBookings();

    public String addBookingDto(DetailedBookingDto bookingDto);

    public boolean checkBookingsByCustomerId(Long id);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking, Customer customer, Room room);

    public void deleteBookingById(Long id);
    public Booking getById(Long id);
}