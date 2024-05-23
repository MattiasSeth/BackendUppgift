package com.example.backenduppgift.Email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Locale;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/email")
public class EmailController {

    private final EmailService emailService;

    /*
     * Handle GET requests to /send
     */
    @RequestMapping(value = "/send", method = {RequestMethod.GET, RequestMethod.POST})
    public void sendMailWithInline()
            throws MessagingException, IOException {

        // Hardcoded values for testing
        Locale locale = new Locale("en");
        String recipientName = "John Doe";
        String recipientEmail = "johndoe@example.com";

        Path imagePath = Path.of("C:\\Users\\harry\\IdeaProjects\\BackendUppgift\\src\\main\\resources\\static\\cat.jpg");
        byte[] imageBytes = Files.readAllBytes(imagePath);
        String imageName = "cat.jpg"; // Replace with actual image name
        String imageContentType = "image/jpeg"; // Replace with actual image content type


        // Call the service method with the hardcoded values
        this.emailService.sendMailWithInline(
                recipientName, recipientEmail, imageName,
                imageBytes, imageContentType, locale);


    }
}
