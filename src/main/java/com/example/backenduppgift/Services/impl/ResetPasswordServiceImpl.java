package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.ForgottenPasswordToken;
import com.example.backenduppgift.Repositories.ResetPasswordTokenRepo;
import com.example.backenduppgift.Security.User;
import com.example.backenduppgift.Security.UserRepository;
import com.example.backenduppgift.Services.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final UserRepository userRepo;

    private final ResetPasswordTokenRepo tokenRepo;

    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public ResetPasswordServiceImpl(UserRepository userRepo, ResetPasswordTokenRepo tokenRepo, JavaMailSender mailSender, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void createTokenForUser(User user, String token) {
        ForgottenPasswordToken userToken = new ForgottenPasswordToken();
        userToken.setToken(token);
        userToken.setUser(user);
        long now = System.currentTimeMillis();
        long expireDateTime = now + (24 * 60 * 60 * 1000);
        userToken.setExpireDate(new Date(expireDateTime));
        tokenRepo.save(userToken);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        ForgottenPasswordToken passToken = tokenRepo.findByToken(token);
        User user = passToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        tokenRepo.delete(passToken);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.getUserByUsername(email);
    }

    @Override
    public void sendEmailResetPassword(String email) {
        User user = findUserByEmail(email);
        SimpleMailMessage message = new SimpleMailMessage();

        if (user != null) {
            String token = UUID.randomUUID().toString();
            createTokenForUser(user, token);
            String messageWithtUrl =  "Klicka här för att återställa ditt lösenord" + " http://localhost:8080/reset-password?token=" + token;

            message.setTo(user.getUsername());
            message.setSubject("Lösenordsåterställning");
            message.setText(messageWithtUrl);
            message.setFrom("noreply@backendTwo.se");
            mailSender.send(message);
        }
    }
}
