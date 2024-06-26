package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Services.BookingService;
import com.example.backenduppgift.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final BookingService bookingService;


    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<CustomerDto> customers = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("customerTitle", "All Customers");
        model.addAttribute("addCustomer", "Add Customers");
        return "showAllCustomers";
    }

    @RequestMapping("/add")
    public String addCustomer(){
        return "addCustomer.html";
    }
    @PostMapping("/addDone")
    public String addCustomerDone(@RequestParam String name, @RequestParam String email, Model model){
        CustomerDto customer = new CustomerDto();
        customer.setName(name);
        customer.setEmail(email);
        customerService.addCustomer(customer);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "redirect:/customer/all";
    }

     @RequestMapping("/delete")
    public String getAllWithDelete (Model model) {
        List<CustomerDto> customers = customerService.getAllCustomers();
         model.addAttribute("allCustomers", customers);
         model.addAttribute("customerTitle", "All Customers");
         return "redirect:/customer/all";
     }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteCap(@PathVariable Long id, Model model) {
        boolean bookings = bookingService.checkBookingsByCustomerId(id);
        if (!bookings)
            customerService.deleteCustomerById(id);
        return getAllWithDelete(model);
    }

    @RequestMapping(path = "/edit/{id}")
    public String editName (@PathVariable Long id, Model model){
        Customer customer = customerService.getById(id);
        model.addAttribute("Customer", customer);
        return "editNameForm";
    }

    @PostMapping("/update")
    public String updateCustomerName(Model model, CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
        List<CustomerDto> customers = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("customerTitle", "All Customers");
        model.addAttribute("addCustomer", "Add Customers");
        return "showAllCustomers";
    }




}
