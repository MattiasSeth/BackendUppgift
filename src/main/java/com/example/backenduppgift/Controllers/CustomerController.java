package com.example.backenduppgift.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        List<CustomerDto> customers = customerService.getAllCustomers();
        model.addAttribute("allCustomers", customers);
        model.addAttribute("customerTitle", "All Customers");
        return "showAllCustomers";
    }

    @RequestMapping("/add")
    public String addCustomer(){
        return "addCustomer.html";
    }
    @PostMapping("/addDone")
    public String addCustomerDone(@RequestParam String fname, @RequestParam String lname, Model model){
        CustomerDto customer = new CustomerDto();
        customer.setName(fname +" " +lname);
        customerService.addCustomer(customer);

        model.addAttribute("fname", fname);
        model.addAttribute("lname", lname);
        return "addCustomerDone.html";
    }


    /*
    @PostMapping("customer/delete")
    public List<CustomerDto> deleteCustomerDto(@RequestBody CustomerDto customerDto){
        customerService.getAllCustomers().remove(customerDto);
        return customerService.getAllCustomers();
    }
    */
     @RequestMapping("/deleteCustomer")
    public String getAllWithDelete (Model model) {
        List<CustomerDto> customers = customerService.getAllCustomers();
         model.addAttribute("allCustomers", customers);
         model.addAttribute("customerTitle", "All Customers");
         return "deleteCustomer";
     }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteCap(@PathVariable Long id, Model model) {
        customerService.deleteCustomerById(id);
        return getAllWithDelete(model);
    }



}
