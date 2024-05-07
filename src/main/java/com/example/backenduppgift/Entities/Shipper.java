package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipper {

    @Id
    @GeneratedValue
    private Long id; // Database Id

    private int externalId; // Json Id
    private String companyName;
    private String phone;
}
