package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.Entities.Booking;
import com.example.backenduppgift.Repositories.BookingRepository;
import com.example.backenduppgift.Services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    final private BookingRepository bookingRepository;

    @Override
    public double getFinalPrice(Long customerId, double totalPrice) {
        Long totalDays = 0L;
        List<Booking> customerBookings = new ArrayList<>();
        List<Booking> allBookings = bookingRepository.findAll();

        for (Booking tempBooking : allBookings) {
            if (tempBooking.getCustomer().getId() == customerId) {
                customerBookings.add(tempBooking);
            }
        }
        for (Booking tempBooking : customerBookings) {
            totalDays = totalDays + ChronoUnit.DAYS.between(tempBooking.getStartDate(), tempBooking.getEndDate());
        }
        if (totalDays >= 10){
            return totalPrice*0.98;
        }else
            return totalPrice;
    }
    @Override
    public double calculatePrice(LocalDate startDate, LocalDate endDate, int price){
        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate tempDate = startDate;
        int sundays = 0;
        double sundayDiscount = 0.98;
        double dailyDiscount = 0.995;
        double result = 0;

        while (!tempDate.isAfter(endDate.minusDays(1))) {
            if(tempDate.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY"))
                sundays++;
            tempDate = tempDate.plusDays(1);
        }

        if (nights < 2 && sundays < 1){
            return  price;
        } else if (nights < 2 && sundays == 1) {
            return  price*sundayDiscount;
        }else if (nights > 1 && sundays == 0) {
            return price*nights*dailyDiscount;
        } else {
            nights = nights-sundays;
            result = (nights*price*dailyDiscount)+(sundays*price*dailyDiscount*sundayDiscount);
            return result;
        }
    }
}
