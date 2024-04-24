package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.DTO.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping("customer")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
