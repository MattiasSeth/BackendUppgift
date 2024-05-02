package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        CustomerDto customer1 = new CustomerDto(1L, "Gustav");
        RoomDto room1 = new RoomDto(1L, "Single", 1);
        DetailedBookingDto booking1 = new DetailedBookingDto(1L, customer1, room1, 0, LocalDate.now(), LocalDate.now().plusDays(1));

        CustomerDto customer2 = new CustomerDto(2L, "Double");
        RoomDto room2 = new RoomDto(2L, "Double", 2);
        DetailedBookingDto booking2 = new DetailedBookingDto(2L, customer2, room2, 1, LocalDate.now(), LocalDate.now().plusDays(2));

        List<DetailedBookingDto> bookings = Arrays.asList(booking1, booking2);

        when(bookingService.getAllBookings()).thenReturn(bookings);
    }


    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/bookings/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allBookings"))
                .andExpect(view().name("showAllOccupiedrooms"));
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    public void testDeleteBooking() throws Exception {
        mockMvc.perform(get("/bookings/deleteById/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bookings/all"));
        verify(bookingService, times(1)).deleteBookingById(1L);
    }

    @Test
    public void testAddBooking() throws Exception {
        mockMvc.perform(post("/bookings/addReceiver")
                        .param("customerName", "John Doe")
                        .param("startDate", "2022-01-01")
                        .param("endDate", "2022-01-02"))
                .andExpect(status().isOk())
                .andExpect(view().name("displayAvalibleRooms"));
    }
}
