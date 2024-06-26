package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BlacklistDto;
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

    public Customer getByName(String name);

    public void saveCustomer(Customer customer);

    public Customer getByEmail(String email);

    public void updateCustomer(CustomerDto customerDto);
}
