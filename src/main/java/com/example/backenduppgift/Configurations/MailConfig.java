package com.example.backenduppgift.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import java.util.Properties;
@Configuration
public class MailConfig {

    @Autowired
    IntegrationProperties properties;

    @Bean
    JavaMailSender createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(properties.getMail().getHost());
        mailSender.setPort(properties.getMail().getPort());
        mailSender.setUsername(properties.getMail().getEmail());
        mailSender.setPassword(properties.getMail().getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.debug","true");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }
}


