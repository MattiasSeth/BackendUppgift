package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Repositories.BookingRepository;
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
class BookingControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingRepository mockRepo;

    @BeforeEach
    public void init(){
        // Add values to b1,b2,b3,b4
        Booking b1 = new Booking();
        Booking b2 = new Booking();
        Booking b3 = new Booking();
        Booking b4 = new Booking();

        when(mockRepo.findById(1L)).thenReturn(Optional.of(b1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(b2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(b3));
        when(mockRepo.findById(4L)).thenReturn(Optional.of(b4));
        when(mockRepo.findAll()).thenReturn(Arrays.asList(b1,b2,b3,b4));
    }

}