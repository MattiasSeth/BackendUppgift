package com.example.backenduppgift.Services;

import com.example.backenduppgift.Security.User;

public interface ResetPasswordService {
    public void createTokenForUser(User user, String token);
    public void resetPassword(String token, String newPassword);
    public User findUserByEmail(String email);
    public void sendEmailResetPassword(String email);
}
