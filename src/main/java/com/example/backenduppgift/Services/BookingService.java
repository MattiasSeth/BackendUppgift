package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BookingDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Entities.Customer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public DetailedBookingDto bookingToDetailedBookingDto(Booking b);

    public Booking bookingToDetailedBookingDto(DetailedBookingDto b);

    public List<DetailedBookingDto> getAllBookings();

    public String addBookingDto(DetailedBookingDto bookingDto);

    public boolean checkBookingsByCustomerId(Long id);

    public void deleteBookingById(Long id);
    public Booking getById(Long id);

    public List<RoomDto> findAvailableRooms(LocalDate startDate, LocalDate endDate);

    public void addNewBookingFromEdit(Booking booking);

}