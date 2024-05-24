package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Configurations.IntegrationProperties;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Email.EmailService;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Services.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;
    private final BlacklistService blacklistService;
    private final DiscountService discountService;
    private final EmailService emailService;

    //@Autowired
    //JavaMailSender javaMailSender;

    @Autowired
    IntegrationProperties properties;


    @RequestMapping("/all")
    public String getAllBookings(Model model){
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("allBookings", bookings);
        model.addAttribute("roomTitle", "All occupied rooms");
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
    @RequestMapping(path="/add")
    public String addBookings(){
        return "addBooking";
    }

    @PostMapping("/addReceiver")
    public String addBookingsReceiver(@RequestParam String customerName, @RequestParam String customerEmail,
                                      @RequestParam LocalDate startDate, @RequestParam LocalDate endDate, Model model,
                                      HttpSession session){

        // kontrollerar om användaren är blacklistan eller ifall vi ska fortsätta med beställningen
        if (blacklistService.isEpostInBlacklist(customerEmail)) {
            return "blacklist";
        }

        Customer existingCustomer = customerService.getByName(customerName);
        Long customerId;

        if (existingCustomer != null) {
            customerId = existingCustomer.getId();
        }else {
            Customer customer = new Customer(customerName,customerEmail);
            customerService.saveCustomer(customer);
            customerId = customer.getId();
        }
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
        Room room = roomService.getByIdToRoom(id);

        model.addAttribute("roomId", id);
        model.addAttribute("roomPrice", room.getPrice());
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
    Customer customer = customerService.getById(customerId);
    Room room = roomService.getByIdToRoom(roomId);

    double tempDiscount = discountService.calculatePrice(startDate,endDate,room.getPrice());
    double finalPrice = discountService.getFinalPrice(customer.getId(),tempDiscount);

    Booking newBooking = new Booking(customer,room,extraBeds,startDate,endDate,finalPrice);
    bookingService.addNewBookingFromEdit(newBooking);

    session.setAttribute("bookingId", newBooking.getId());

    return "forward:/bookings/email";
    }
    @RequestMapping(value = "/email", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendMailWithInline(HttpSession session)
            throws MessagingException, IOException {

        Long bookingId = (Long) session.getAttribute("bookingId");
        Booking booking = bookingService.getById(bookingId);

        String recipientName = booking.getCustomer().getName();
        String recipientEmail = booking.getCustomer().getEmail();

        LocalDate checkIn = booking.getStartDate();
        LocalDate checkOut = booking.getEndDate();
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        String roomType = booking.getRoom().getRoomType();
        double price =  Math.round(booking.getTotalPrice() * 100.0) / 100.0;
        // Image
        Path imagePath = Path.of("src/main/resources/static/cat.jpg");
        byte[] imageBytes = Files.readAllBytes(imagePath);
        String imageName = "cat.jpg";
        String imageContentType = "image/jpeg";

        this.emailService.sendMailWithInline(
                recipientName, recipientEmail, imageName,
                imageBytes, imageContentType, checkIn, checkOut, roomType, nights, price);

        return "redirect:/bookings/all";
    }

    @RequestMapping("/searchRoom")
    public String searchRooms(){
        return "searchRoom";
    }

    @GetMapping("/result")
    public String searchRooms(@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate startDate, @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate endDate, Model model){
        List<RoomDto> availableRooms =  bookingService.findAvailableRooms(startDate, endDate);
        model.addAttribute("availableRooms", availableRooms);
        return "result";
    }

    @GetMapping("/test")
    public String testFinalPrice(Model model){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(10);

        double discount = discountService.calculatePrice(startDate,endDate,1000);
        double result = discountService.getFinalPrice(1L,discount);
        model.addAttribute("finalPrice", result);
        return "test";
    }
}
