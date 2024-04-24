package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @RequestMapping("Bookings")
    public List<DetailedBookingDto> getAllBookingsDto(){
        return bookingService.getAllBookings();
    }

    @PostMapping("Bookings/add")
    public String addBookingDto(@RequestBody DetailedBookingDto bookingDto){
       return bookingService.addBookingDto(bookingDto);
    }

    @RequestMapping("Bookings/delete")
    public List<DetailedBookingDto> deleteBookingDto(@RequestBody DetailedBookingDto bookingDto){
        // TODO anropa BookingService.delete() som inte finns.
        return new ArrayList<>();
    }
}
