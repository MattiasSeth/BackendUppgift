package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer = new Customer(1L, "Nadia");
        when(customerRepository.findAll()).thenReturn(List.of(customer));
        List<CustomerDto> customers = customerService.getAllCustomers();
        assertFalse(customers.isEmpty());
        assertEquals("Nadia", customers.get(0).getName());
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(1L, "Sigrun");
        CustomerDto customerDto = new CustomerDto(1L, "Sigrun");
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.saveCustomer(customer);
        verify(customerRepository).save(customer);
    }

    @Test
    public void testDeleteCustomerById() {
        doNothing().when(customerRepository).deleteById(anyLong());
        customerService.deleteCustomerById(1L);
        verify(customerRepository).deleteById(1L);
    }
}
