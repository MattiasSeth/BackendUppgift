package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.Entities.Blacklist;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.DTO.CustomerDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepository customerRepository;
    @Override
    public CustomerDto customerToCustomerDto(Customer c) {
        return CustomerDto.builder().id(c.getId())
                .name(c.getName()).email(c.getEmail())
                .build();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(c->customerToCustomerDto(c)).toList();
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public String addCustomer(CustomerDto c) {
        customerRepository.save(customerDtoToCustomer(c));
        return "Customer saved";
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDto c) {
        return Customer.builder().id(c.getId())
                .name(c.getName()).email(c.getEmail())
                .build();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer getByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Customer getByEmail(String email){return customerRepository.findByEmail(email);}

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(customerDto.getId()).get();
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setName(customerDto.getName());

        customerRepository.save(existingCustomer);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }


}
