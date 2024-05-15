package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "Albin Karlsson", "albin.karl@example.com");
        customerDto = CustomerDto.builder()
                .id(1L)
                .name("Albin Karlsson")
                .email("albin.karl@example.com")
                .build();
    }

    @Test
    void customerToCustomerDto() {
        CustomerDto dto = customerService.customerToCustomerDto(customer);
        assertNotNull(dto);
        assertEquals(customer.getId(), dto.getId());
        assertEquals(customer.getName(), dto.getName());
        assertEquals(customer.getEmail(), dto.getEmail());
    }

    @Test
    void customerDtoToCustomer() {
        Customer c = customerService.customerDtoToCustomer(customerDto);
        assertNotNull(c);
        assertEquals(customerDto.getId(), c.getId());
        assertEquals(customerDto.getName(), c.getName());
        assertEquals(customerDto.getEmail(), c.getEmail());
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        assertEquals(1, customerDtos.size());
        assertEquals(customer.getId(), customerDtos.get(0).getId());
    }

    @Test
    void deleteCustomerById() {
        doNothing().when(customerRepository).deleteById(anyLong());
        customerService.deleteCustomerById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void addCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        String result = customerService.addCustomer(customerDto);
        assertEquals("Customer saved", result);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.getById(1L);
        assertNotNull(foundCustomer);
        assertEquals(customer.getId(), foundCustomer.getId());
    }

    @Test
    void getByName() {
        when(customerRepository.findByName(anyString())).thenReturn(customer);
        Customer foundCustomer = customerService.getByName("Albin Karlsson");
        assertNotNull(foundCustomer);
        assertEquals(customer.getName(), foundCustomer.getName());
    }

    @Test
    void getByEmail() {
        when(customerRepository.findByEmail(anyString())).thenReturn(customer);
        Customer foundCustomer = customerService.getByEmail("albin.karl@example.com");
        assertNotNull(foundCustomer);
        assertEquals(customer.getEmail(), foundCustomer.getEmail());
    }

    @Test
    void updateCustomer() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto updatedDto = CustomerDto.builder()
                .id(1L)
                .name("Albin Karlsson")
                .email("albin.karl@example.com")
                .build();

        customerService.updateCustomer(updatedDto);

        assertEquals(updatedDto.getName(), customer.getName());
        assertEquals(updatedDto.getEmail(), customer.getEmail());

        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void saveCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }
}
