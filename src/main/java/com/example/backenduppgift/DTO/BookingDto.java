package com.example.backenduppgift.DTO;

import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Long id;

    private Customer customer;
    private Room room;

    private int extraBeds;
    private LocalDate startDate;
    private LocalDate endDate;

}
