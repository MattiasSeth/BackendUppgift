package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.DTO.CustomerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    final private CustomerRepository cr;
    @Override
    public CustomerDto customerToCustomerDto(Customer c) {
        return CustomerDto.builder().id(c.getId()).name(c.getName()).build();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return cr.findAll().stream().map(c->customerToCustomerDto(c)).toList();
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long id) {
        cr.deleteById(id);
    }

    @Override
    public String addCustomer(CustomerDto c) {
        cr.save(customerDtoToCustomer(c));
        return "Customer saved";
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto c) {
        return Customer.builder().id(c.getId()).name(c.getName()).build();
    }

    @Override
    public Customer getById(Long id) {
        return cr.findById(id).get();
    }

    @Override
    public Customer getByName(String name) {
        return cr.findByName(name);
    }


}
