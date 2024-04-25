package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository mockRepo;

    @BeforeEach
    public void init(){
        Customer c1 = new Customer(1L,"S");
        Customer c2 = new Customer(2L,"E");
        Customer c3 = new Customer(3L,"T");
        Customer c4 = new Customer(4L,"H");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(c2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(c3));
        when(mockRepo.findById(4L)).thenReturn(Optional.of(c4));
        when(mockRepo.findAll()).thenReturn(Arrays.asList(c1,c2,c3,c4));
    }

}