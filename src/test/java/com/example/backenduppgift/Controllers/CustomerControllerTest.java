package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    public void init(){

        Customer c1 = new Customer(1L,"S");
        Customer c2 = new Customer(2L,"E");
        Customer c3 = new Customer(3L,"T");
        Customer c4 = new Customer(4L,"H");

        List<Customer> customers = Arrays.asList(c1, c2, c3, c4);

        when(customerService.getAllCustomers()).thenReturn(
                customers.stream().map(this::customerToCustomerDto).toList()
        );
    }

    private CustomerDto customerToCustomerDto(Customer c) {
        return CustomerDto.builder().id(c.getId()).name(c.getName()).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        this.mvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"S\"},{\"id\":2,\"name\":\"E\"}," +
                        "{\"id\":3,\"name\":\"T\"},{\"id\":4,\"name\":\"H\"}]"));

    }
}


