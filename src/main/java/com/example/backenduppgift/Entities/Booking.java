package com.example.backenduppgift.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue
    private long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Room room;
}
