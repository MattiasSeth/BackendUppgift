package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blacklist {

    @Id
    @GeneratedValue
    private Long id;  // database ID

    private int externalId;  // json ID
    private String email;
    private String name;
    private String customerGroup;
    private Date created;
    private boolean ok;

    public boolean getOk() {
        return ok;
    }
}
