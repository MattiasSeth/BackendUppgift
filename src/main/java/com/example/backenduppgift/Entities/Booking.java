package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private long bookingId;
    private Date startDate;
    private Date endDate;
}
