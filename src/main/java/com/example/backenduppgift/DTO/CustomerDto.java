package com.example.backenduppgift.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private String name;
    private String email;

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
