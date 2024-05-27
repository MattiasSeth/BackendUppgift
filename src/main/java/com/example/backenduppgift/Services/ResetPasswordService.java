package com.example.backenduppgift.Services;

import com.example.backenduppgift.ForgottenPasswordToken;
import com.example.backenduppgift.Repositories.ResetPasswordTokenRepo;
import com.example.backenduppgift.Security.RoleRepository;
import com.example.backenduppgift.Security.User;
import com.example.backenduppgift.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;
@Service
public class ResetPasswordService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ResetPasswordTokenRepo tokenRepo;



    //Vi skapar en token för användaren
    public void createPasswordResetTokenForUser(User user, String token) {
        ForgottenPasswordToken myToken = new ForgottenPasswordToken();
        myToken.setToken(token);
        myToken.setUser(user);
        myToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 ));
        tokenRepo.save(myToken);
    }

    // Vi resettar lösenordet
    public void resetPassword(String token, String newPassword) {
        ForgottenPasswordToken passToken = tokenRepo.findByToken(token);
        User user = passToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepo.save(user);
        tokenRepo.delete(passToken);
    }

    // Vi letar efter användaren
    public User findUserByEmail(String email) {
        return userRepo.getUserByUsername(email);
    }

    // Vi skickar mejl
    public void sendPasswordResetEmail(String email) {
        User user = findUserByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            createPasswordResetTokenForUser(user, token);
            String resetUrl = "http://localhost:8080/reset-password?token=" + token;
            //sendEmail(email, "Password Reset Request", "To reset your password, click the link below:\n" + resetUrl);
        }
    }
}
