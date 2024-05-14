package com.example.backenduppgift.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Queue {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name="Id")
    private UUID id;

    @Column(name="Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @Column(name="MessagesToSend")
    private int messagesToSend;

    public Queue(String name, Room room, int messagesToSend) {
        this.name = name;
        this.room = room;
        this.messagesToSend = messagesToSend;
    }
}
