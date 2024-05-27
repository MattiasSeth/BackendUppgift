package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.ForgottenPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordTokenRepo extends JpaRepository<ForgottenPasswordToken, Long> {
    ForgottenPasswordToken findByToken(String token);
}
