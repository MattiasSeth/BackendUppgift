package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping("customer")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("customer/add")
    public List<CustomerDto> addCustomerDto(@RequestBody CustomerDto customerDto){
        customerService.getAllCustomers().add(customerDto);
        return customerService.getAllCustomers();
    }

    @RequestMapping("customer/delete")
    public List<CustomerDto> deleteCustomerDto(@RequestBody CustomerDto customerDto){
        customerService.getAllCustomers().remove(customerDto);
        return customerService.getAllCustomers();
    }
}
