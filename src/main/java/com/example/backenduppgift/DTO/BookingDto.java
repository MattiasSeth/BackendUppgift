package com.example.backenduppgift.DTO;

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
    private int extraBeds;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
}
