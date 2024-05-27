package com.example.backenduppgift;

import com.example.backenduppgift.Security.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class ForgottenPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expireDate;
}
