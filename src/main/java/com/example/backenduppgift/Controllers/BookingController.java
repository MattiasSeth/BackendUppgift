package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Services.BookingService;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Services.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;

    @RequestMapping("/all")
    public String getAllBookings(Model model){
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookings);
        model.addAttribute("roomTitle", "Bookings");
        //model.addAttribute("addCustomer", "Add Customers");
        return "showAllOccupiedrooms";
    }


    @RequestMapping("/delete")
    public String getAllWithDelete (Model model) {
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookings);
        model.addAttribute("roomTitle", "All occupied rooms");
        return "redirect:/bookings/all";
    }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteCap(@PathVariable Long id, Model model) {
        bookingService.deleteBookingById(id);
        return getAllWithDelete(model);
    }

    @RequestMapping(path = "/edit/{id}")
    public String editBooking(@PathVariable Long id, Model model, HttpSession session){
        Booking booking = bookingService.getById(id);
        Long customerId = booking.getCustomer().getId();

        bookingService.deleteBookingById(id);
        session.setAttribute("customerId", customerId);

        Room room = booking.getRoom();
        return "enterNewDates";
    }

    @PostMapping("/avaliblerooms")
    public String updateBookingDates(@RequestParam("startDate") LocalDate startDate,
                                     @RequestParam("endDate") LocalDate endDate,
                                     Model model, HttpSession session) {

        Long customerId = (Long) session.getAttribute("customerId");
        session.setAttribute("customerId", customerId);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        List<RoomDto> availableRooms = bookingService.findAvailableRooms(startDate, endDate);
        model.addAttribute("availableRooms", availableRooms);

        return "displayAvalibleRooms";
    }
    @RequestMapping(path = "avaliblerooms/extrabeds/{id}")
    public String extraBeds(@PathVariable Long id, Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");
        session.setAttribute("customerId", customerId);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        Room room = roomService.getById(id);

        model.addAttribute("roomId", id);
        model.addAttribute("roomType", room.getRoomType());
        model.addAttribute("roomSize", room.getSize());
        return "setExtraBeds";
    }
    @PostMapping("/processExtraBeds")
    public String processExtraBeds(@RequestParam("roomId") Long roomId,
                                   @RequestParam("extraBeds") int extraBeds,
                                   HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        LocalDate startDate = (LocalDate) session.getAttribute("startDate");
        LocalDate endDate = (LocalDate) session.getAttribute("endDate");

        return "redirect:/bookings/all";
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
