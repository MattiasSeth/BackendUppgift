package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    @RequestMapping("Bookings")
    public List<BookingDto> getAllBookingsDto(){
        // TODO anropa BookingService som inte finns.
        return new ArrayList<>();
    }

    @RequestMapping("Bookings/add")
    public List<BookingDto> addBookingDto(@RequestBody BookingDto bookingDto){
        // TODO anropa BookingService.save() som inte finns.
        return new ArrayList<>();
    }

    @RequestMapping("Bookings/delete")
    public List<BookingDto> deleteBookingDto(@RequestBody BookingDto bookingDto){
        // TODO anropa BookingService.delete() som inte finns.
        return new ArrayList<>();
    }
}
