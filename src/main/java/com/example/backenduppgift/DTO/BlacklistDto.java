package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("id")
    public int externalId;

    public String email;
    public String name;
    public String group;
    public Date created;
    public boolean ok;

    public boolean getOk() {
        return ok;
    }
}
