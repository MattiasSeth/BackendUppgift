package com.example.backenduppgift.Email;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailTemplate {

    @GeneratedValue
    @Id
    private long id;

    @Lob
    @Column(length = 16777215)
    private String content;

    public EmailTemplate(String content) {
        this.content = content;
    }
}
