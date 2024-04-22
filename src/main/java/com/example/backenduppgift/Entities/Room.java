package com.example.backenduppgift.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING) // tydligare att spara string värdet av enum, istället för int-ordningen.
    private RoomType roomType;
    private int bedQuantity;
}
