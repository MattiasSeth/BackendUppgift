package com.example.backenduppgift.Controllers;

import com.example.backenduppgift.Security.UserRepository;
import com.example.backenduppgift.Services.impl.ResetPasswordServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepo;
    private final ResetPasswordServiceImpl resetPasswordServiceImpl;

    public LoginController(UserRepository userRepo, ResetPasswordServiceImpl resetPasswordServiceImpl) {
        this.userRepo = userRepo;
        this.resetPasswordServiceImpl = resetPasswordServiceImpl;
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPasswordForm(@RequestParam("email") String email, Model model) {

        if(userRepo.getUserByUsername(email)!=null) {
            resetPasswordServiceImpl.sendEmailResetPassword(email);
            model.addAttribute("message", "Ett mejl med länk för lösenordsåterställning har blivit skickat till ditt mejl.");
        }
        else{
            model.addAttribute("message", "Kontot finns inte, försök igen.");
        }

        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPasswordForm(@RequestParam("token") String token,
                                           @RequestParam("password") String password,
                                           Model model) {
        resetPasswordServiceImpl.resetPassword(token, password);
        model.addAttribute("message", "Din lösenord har nu ändrats.");
        return "reset-password";

    }
}
