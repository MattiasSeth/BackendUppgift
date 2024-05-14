package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    //@NotNull
    //@NotEmpty
    private int price;
    @NotNull
    @NotEmpty(message = "Room type cant be empty, please input singel or double")
    private String roomType;  // single or double
    @Min(value = 0, message = "Size needs to be between 0-2")
    @Max(value = 2, message = "Size needs to be between 0-2")
    private int size;   // 0-2 (antal sängar)

    public Room (String roomType, int size, int price){
        this.roomType = roomType;
        this.size = size;
        this.price=price;
    }
}
