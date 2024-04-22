package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    private String roomType;  // single or double
    private int size;   // 0-2 (antal sängar)

    public Room (String roomType, int size){
        this.roomType = roomType;
        this.size = size;
    }
}
