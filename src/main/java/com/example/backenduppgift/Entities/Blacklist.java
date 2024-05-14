package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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

    //@NotEmpty(message = "Email cannot be empty")
    //@Email(message = "Invalid email-address, the email needs to be a real email address" )
    private String email;

    //@NotEmpty(message = "Name cannot be empty")
    private String name;

    private String customerGroup;
    private Date created;
    private boolean ok;

    public boolean getOk() {
        return ok;
    }
}
