package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.BookingDto;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Services.RoomService;
import com.example.backenduppgift.Services.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Booking booking;
    private DetailedBookingDto detailedBookingDto;

    @BeforeEach
    void setUp() {
        // Load data from XML or other resources
        InputStream is = getClass().getClassLoader().getResourceAsStream("test-data.xml");
        // Parse XML and set up your test data here

        // Example setup
        Customer customer = new Customer(1L, "John Doe", "john.doe@example.com");
        Room room = new Room(1L, 100, "Single", 1);
        booking = new Booking(customer, room, 1, LocalDate.now(), LocalDate.now().plusDays(1));
        detailedBookingDto = DetailedBookingDto.builder()
                .id(1L)
                .extraBeds(1)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .customer(new CustomerDto(1L, "John Doe", "john.doe@example.com"))
                .room(new RoomDto(1L, 100, "Single", 1))
                .build();
    }

    @Test
    void testBookingToDetailedBookingDto() {
        DetailedBookingDto dto = bookingService.bookingToDetailedBookingDto(booking);
        assertNotNull(dto);
        assertEquals(booking.getId(), dto.getId());
        assertEquals(booking.getExtraBeds(), dto.getExtraBeds());
        assertEquals(booking.getStartDate(), dto.getStartDate());
        assertEquals(booking.getEndDate(), dto.getEndDate());
        assertEquals(booking.getCustomer().getId(), dto.getCustomer().getId());
        assertEquals(booking.getRoom().getId(), dto.getRoom().getId());
    }

    @Test
    void testDetailedBookingDtoToBooking() {
        Booking b = bookingService.bookingToDetailedBookingDto(detailedBookingDto);
        assertNotNull(b);
        assertEquals(detailedBookingDto.getId(), b.getId());
        assertEquals(detailedBookingDto.getExtraBeds(), b.getExtraBeds());
        assertEquals(detailedBookingDto.getStartDate(), b.getStartDate());
        assertEquals(detailedBookingDto.getEndDate(), b.getEndDate());
        assertEquals(detailedBookingDto.getCustomer().getId(), b.getCustomer().getId());
        assertEquals(detailedBookingDto.getRoom().getId(), b.getRoom().getId());
    }

    @Test
    void testGetAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<DetailedBookingDto> dtos = bookingService.getAllBookings();
        assertEquals(1, dtos.size());
        assertEquals(booking.getId(), dtos.get(0).getId());
    }

    @Test
    void testAddBookingDto() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        String result = bookingService.addBookingDto(detailedBookingDto);
        assertEquals("The booking has been saved", result);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void testCheckBookingsByCustomerId() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        boolean exists = bookingService.checkBookingsByCustomerId(1L);
        assertTrue(exists);

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        exists = bookingService.checkBookingsByCustomerId(2L);
        assertFalse(exists);
    }

    @Test
    void testDeleteBookingById() {
        doNothing().when(bookingRepository).deleteById(anyLong());
        bookingService.deleteBookingById(1L);
        verify(bookingRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testGetById() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));
        Booking result = bookingService.getById(1L);
        assertNotNull(result);
        assertEquals(booking.getId(), result.getId());
    }

    @Test
    void testFindAvailableRooms() {
        List<RoomDto> allRooms = List.of(
                new RoomDto(1L, 100, "Single", 1),
                new RoomDto(2L, 150, "Double", 2)
        );
        when(roomService.getAllRooms()).thenReturn(allRooms);
        when(bookingRepository.findAll()).thenReturn(List.of(booking));

        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(3);
        List<RoomDto> availableRooms = bookingService.findAvailableRooms(startDate, endDate);
        assertEquals(2, availableRooms.size());

        startDate = LocalDate.now();
        endDate = LocalDate.now().plusDays(1);
        availableRooms = bookingService.findAvailableRooms(startDate, endDate);
        assertEquals(1, availableRooms.size());
        assertEquals(2L, availableRooms.get(0).getId());
    }

    @Test
    void testAddNewBookingFromEdit() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        bookingService.addNewBookingFromEdit(booking);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
}
