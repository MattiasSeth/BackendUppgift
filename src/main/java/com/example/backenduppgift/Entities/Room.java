package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String roomType;  // single or double
    @Min(value = 0)
    @Max(value = 2)
    private int size;   // 0-2 (antal sängar)

    public Room (String roomType, int size){
        this.roomType = roomType;
        this.size = size;
    }
}
