package com.example.backenduppgift.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email-address, the email needs to be a real email address" )
    private String email;

    public Customer (@NotNull String name, @NotNull String email){
        this.name = name;
        this.email = email;
    }

}
