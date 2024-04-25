package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.RoomRepository;
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
class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomRepository mockRepo;

    @BeforeEach
    public void init(){
        Room r1 = new Room(1L,"S",1);
        Room r2 = new Room(2L,"E",2);
        Room r3 = new Room(3L,"T",3);
        Room r4 = new Room(4L,"H",4);

        when(mockRepo.findById(2L)).thenReturn(Optional.of(r1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(r2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(r3));
        when(mockRepo.findById(4L)).thenReturn(Optional.of(r4));
        when(mockRepo.findAll()).thenReturn(Arrays.asList(r1,r2,r3,r4));
    }

}