package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Services.BookingService;
import com.example.backenduppgift.Services.CustomerService;
import com.example.backenduppgift.Services.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    final private BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Override
    public DetailedBookingDto bookingToDetailedBookingDto(Booking b) {
        return DetailedBookingDto.builder().id(b.getId())
                .extraBeds(b.getExtraBeds())
                .startDate(b.getStartDate())
                .endDate(b.getEndDate())
                .totalPrice(b.getTotalPrice())
                .customer(new CustomerDto(b.getCustomer().getId(),b.getCustomer().getName(), b.getCustomer().getEmail()))
                .room(new RoomDto(b.getRoom().getId(),b.getRoom().getPrice(), b.getRoom().getRoomType(),b.getRoom().getSize())).build();
    }

    @Override
    public Booking bookingToDetailedBookingDto(DetailedBookingDto bookingDto) {
        return Booking.builder().id(bookingDto.getId())
                .extraBeds(bookingDto.getExtraBeds())
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .totalPrice(bookingDto.getTotalPrice())
                .customer(new Customer(bookingDto.getCustomer().getId(), bookingDto.getCustomer().getName(),bookingDto.getCustomer().getEmail()))
                .room(new Room(bookingDto.getRoom().getId(),bookingDto.getRoom().getPrice(), bookingDto.getRoom().getRoomType()
                        ,bookingDto.getRoom().getSize())).build();
    }

    @Override
    public List<DetailedBookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(b->bookingToDetailedBookingDto(b)).toList();
    }

    @Override
    public String addBookingDto(DetailedBookingDto bookingDto) {
        bookingRepository.save(bookingToDetailedBookingDto(bookingDto));
        return "The booking has been saved";
    }

    @Override
    public boolean checkBookingsByCustomerId(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.isPresent();
    }

    @Override
    @Transactional
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public List<RoomDto> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<DetailedBookingDto> currentBookings = getAllBookings();
        List<RoomDto> allRooms = roomService.getAllRooms();
        List<RoomDto> availableRooms = new ArrayList<>(allRooms);

        for (DetailedBookingDto booking : currentBookings) {
            LocalDate bookingStartDate = booking.getStartDate();
            LocalDate bookingEndDate = booking.getEndDate();

            if (!(endDate.isBefore(bookingStartDate) || startDate.isAfter(bookingEndDate))) {
                Long roomId = booking.getRoom().getId();
                availableRooms.removeIf(room -> room.getId().equals(roomId));
            }
        }
        return availableRooms;
    }

    @Override
    public void addNewBookingFromEdit(Booking booking) {
        bookingRepository.save(booking);
    }

}
