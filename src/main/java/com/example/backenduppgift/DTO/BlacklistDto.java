package com.example.backenduppgift.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlacklistDto {
    public int id;
    public String email;
    public String name;
    public String group;
    public Date created;
    public boolean ok;
}
