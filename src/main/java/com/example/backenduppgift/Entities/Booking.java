package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Room room;

    @NotEmpty(message = "Extra beds cant be empty")
    private int extraBeds;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Customer customer, Room room, int extraBeds, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.room = room;
        this.extraBeds = extraBeds;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
