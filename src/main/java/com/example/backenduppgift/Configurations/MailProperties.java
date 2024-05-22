package com.example.backenduppgift.Configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailProperties {
    private String email;
    private String password;
    private String host;
    private int port;
}

