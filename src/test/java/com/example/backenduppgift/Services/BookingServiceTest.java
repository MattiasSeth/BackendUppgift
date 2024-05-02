package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Services.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBookings() {
        Customer customer = new Customer(1L, "Gustav");
        Room room = new Room(1L, "Single", 1);
        Booking booking = new Booking(1L, customer, room, 0, LocalDate.now(), LocalDate.now().plusDays(1));
        when(bookingRepository.findAll()).thenReturn(List.of(booking));
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        DetailedBookingDto returnedBooking = bookings.get(0);
        assertEquals(customer.getId(), returnedBooking.getCustomer().getId());
        assertEquals(room.getId(), returnedBooking.getRoom().getId());
    }

    @Test
    public void testAddBooking() {
        Customer customer = new Customer(1L, "Seth");
        Room room = new Room(1L, "Single", 0);
        DetailedBookingDto bookingDto = new DetailedBookingDto(1L, new CustomerDto(customer.getId(), customer.getName()), new RoomDto(room.getId(), room.getRoomType(), room.getSize()), 1, LocalDate.now(), LocalDate.now().plusDays(1));
        Booking booking = new Booking(1L, customer, room, 1, LocalDate.now(), LocalDate.now().plusDays(1));
        when(bookingRepository.save(any())).thenReturn(booking);

        bookingService.addNewBookingFromEdit(booking);
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    public void testDeleteBookingById() {
        doNothing().when(bookingRepository).deleteById(anyLong());
        bookingService.deleteBookingById(1L);
        verify(bookingRepository).deleteById(1L);
    }

    @Test
    public void testFindBookingById() {
        Customer customer = new Customer(1L, "Edgar");
        Room room = new Room(1L, "Double", 2);
        Booking booking = new Booking(1L, customer, room, 2, LocalDate.now(), LocalDate.now().plusDays(1));
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Booking foundBooking = bookingService.getById(1L);
        assertNotNull(foundBooking);
        assertEquals(1L, foundBooking.getId());
        assertEquals(customer.getName(), foundBooking.getCustomer().getName());
        assertEquals(room.getRoomType(), foundBooking.getRoom().getRoomType());
    }
}
