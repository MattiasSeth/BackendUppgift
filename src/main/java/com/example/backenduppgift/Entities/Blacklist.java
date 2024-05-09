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
    //private String group;  // Removed because mySQL, can change name if we need variable later!
    private Date created;
    private boolean ok;

}
