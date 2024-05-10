package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public long id;

    @JsonProperty("id")
    public int externalId;

    public String email;
    public String name;

    @JsonProperty("group")
    public String customerGroup;

    public Date created;
    public boolean ok;

    public boolean getOk() {
        return ok;
    }

}
