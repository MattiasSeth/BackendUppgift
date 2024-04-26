package com.example.backenduppgift.Controllers;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Repositories.CustomerRepository;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Services.RoomService;
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
class RoomControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoomService roomService;

    @BeforeEach
    public void init(){
        Room c1 = new Room(1L,"S",0);
        Room c2 = new Room(2L,"E",1);
        Room c3 = new Room(3L,"T",2);
        Room c4 = new Room(4L,"H",3);

        List<Room> rooms = Arrays.asList(c1, c2, c3, c4);

        when(roomService.getAllRoomDto()).thenReturn(
                rooms.stream().map(this::roomToRoomDto).toList()
        );
    }

    public RoomDto roomToRoomDto(Room room) {
        return RoomDto.builder().id(room.getId()).size(room.getSize()).roomType(room.getRoomType()).build();
    }

    @Test
    void getAllRooms() throws Exception {
        this.mvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"roomType\":\"S\",\"size\":0}," +
                        "{\"id\":2,\"roomType\":\"E\",\"size\":1}," +
                        "{\"id\":3,\"roomType\":\"T\",\"size\":2}," +
                        "{\"id\":4,\"roomType\":\"H\",\"size\":3}]"));
    }
}