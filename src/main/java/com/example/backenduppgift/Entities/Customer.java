package com.example.backenduppgift.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    private long personnummer;

    private String name;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
