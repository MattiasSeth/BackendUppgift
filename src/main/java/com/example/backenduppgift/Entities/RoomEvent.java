package com.example.backenduppgift.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomEvent {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="Cleaner name")
    private String cleaningByUser;

    @ManyToOne
    private Room room;

    private String type;

    @Column(name="Date")
    private Date timeStamp;

}
