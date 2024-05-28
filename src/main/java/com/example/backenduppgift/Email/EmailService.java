package com.example.backenduppgift.Email;

import com.example.backenduppgift.Entities.Booking;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private final EmailTemplateRepository emailTemplateRepository;

    public void sendMailWithInline(
            final String recipientName,
            final String recipientEmail,
            final String imageResourceName,
            final byte[] imageBytes,
            final String imageContentType,
            final LocalDate checkIn,
            final LocalDate checkOut,
            final String roomType,
            final Long nights,
            final double price)
            throws MessagingException {

        // Prepare the evaluation context
        Locale locale = new Locale("en");
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML
        ctx.setVariable("checkIn", checkIn);
        ctx.setVariable("checkOut", checkOut);
        ctx.setVariable("roomType", roomType);
        ctx.setVariable("nights", nights);
        ctx.setVariable("price", price);

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject("Booking Backend2");
        message.setFrom("Booking@Backend2.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
        message.setText(htmlContent, true); // true = isHtml

        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        message.addInline(imageResourceName, imageSource, imageContentType);

        // Send mail
        this.mailSender.send(mimeMessage);
    }


    public void saveTemplate(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        String content = new String(bytes, StandardCharsets.UTF_8);

        EmailTemplate emailTemplate = new EmailTemplate(content);
        emailTemplateRepository.save(emailTemplate);
    }

    public List<EmailTemplate> getTemplate (){
        return emailTemplateRepository.findAll().stream().toList();
    }

    public EmailTemplate getById(Long id){
        return emailTemplateRepository.findById(id).get();
    }

    public void deleteTemplate (Long id){
        emailTemplateRepository.deleteById(id);
    }

    public void saveTemplate (EmailTemplate emailTemplate){
        emailTemplateRepository.save(emailTemplate);
    }

}


