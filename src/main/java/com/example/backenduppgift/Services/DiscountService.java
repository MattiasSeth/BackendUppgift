package com.example.backenduppgift.Services;

import com.example.backenduppgift.Entities.Booking;

import java.time.LocalDate;

public interface DiscountService {

    public double calculatePrice(LocalDate startDate, LocalDate endDate, int price);

    public double getFinalPrice(Long customerId, double totalPrice);
}
