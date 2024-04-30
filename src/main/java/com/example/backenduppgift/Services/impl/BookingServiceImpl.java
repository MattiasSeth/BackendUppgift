package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.BookingDto;
import com.example.backenduppgift.DTO.CustomerDto;
import com.example.backenduppgift.DTO.DetailedBookingDto;
import com.example.backenduppgift.DTO.RoomDto;
import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Services.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    final private BookingRepository br;
    @Override
    public BookingDto bookingToBookingDto(Booking b) {
        return BookingDto.builder().id(b.getId()).extraBeds(b.getExtraBeds()).startDate(b.getStartDate()).endDate(b.getEndDate()).build();
    }

    @Override
    public DetailedBookingDto bookingToDetailedBookingDto(Booking b) {
        return DetailedBookingDto.builder().id(b.getId()).extraBeds(b.getExtraBeds()).startDate(b.getStartDate())
                .endDate(b.getEndDate()).customer(new CustomerDto(b.getCustomer().getId(),b.getCustomer().getName()))
                .room(new RoomDto(b.getRoom().getId(), b.getRoom().getRoomType(),b.getRoom().getSize())).build();
    }

    @Override
    public Booking bookingToDetailedBookingDto(DetailedBookingDto bookingDto) {
        return Booking.builder().id(bookingDto.getId()).extraBeds(bookingDto.getExtraBeds()).startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate()).customer(new Customer(bookingDto.getCustomer().getId(),bookingDto.getCustomer()
                        .getName())).room(new Room(bookingDto.getRoom().getId(), bookingDto.getRoom().getRoomType()
                        ,bookingDto.getRoom().getSize())).build();
    }

    @Override
    public List<DetailedBookingDto> getAllBookings() {
        return br.findAll().stream().map(b->bookingToDetailedBookingDto(b)).toList();
    }

    @Override
    public String addBookingDto(DetailedBookingDto bookingDto) {
        br.save(bookingToDetailedBookingDto(bookingDto));
        return "The booking has been saved";
    }

    @Override
    public boolean checkBookingsByCustomerId(Long id) {
        Optional<Booking> bookingOptional = br.findById(id);
        return bookingOptional.isPresent();
    }

    @Override
    @Transactional
    public void deleteBookingById(Long id) {
        br.deleteById(id);
    }

}
