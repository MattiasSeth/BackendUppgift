package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipperDto {

    @JsonProperty("id")
    public int externalId;

    public String email;
    public String companyName;
    public String contactName;
    public String contactTitle;
    public String streetAddress;
    public String city;
    public String postalCode;
    public String country;
    public String phone;
    public String fax;
}
