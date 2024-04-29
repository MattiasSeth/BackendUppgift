package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookings);
        model.addAttribute("roomTitle", "All occupied rooms");
        //model.addAttribute("addCustomer", "Add Customers");
        return "showAllOccupiedrooms";
    }



























    @RequestMapping("Bookings")
    public List<DetailedBookingDto> getAllBookingsDto(){
        return bookingService.getAllBookings();
    }

    @PostMapping("Bookings/add")
    public String addBookingDto(@RequestBody DetailedBookingDto bookingDto){
       return bookingService.addBookingDto(bookingDto);
    }

    @PostMapping("Bookings/delete")
    public List<DetailedBookingDto> deleteBookingDto(@RequestBody DetailedBookingDto bookingDto){
        // TODO anropa BookingService.delete() som inte finns.
        return new ArrayList<>();
    }



}
