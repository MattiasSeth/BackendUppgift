package com.example.backenduppgift.Services;

import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.DTO.CustomerDto;

import java.util.List;

public interface CustomerService {
    public CustomerDto customerToCustomerDto(Customer c);

    public List<CustomerDto> getAllCustomers();

    public void deleteCustomerById(Long id);

    public String addCustomer (CustomerDto c);

    public Customer customerDtoToCustomer (CustomerDto c);

    public Customer getById(Long id);


}
