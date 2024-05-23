package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractCustomerDTO {

    @JsonIgnore
    public long id;
    @JsonProperty("externalId")
    @JacksonXmlProperty(localName = "id", isAttribute = true)
    public int externalId;

    @JsonProperty("companyName")
    public String companyName;

    @JsonProperty("contactName")
    public String contactName;

    @JsonProperty("contactTitle")
    public String contactTitle;

    @JsonProperty("streetAddress")
    public String streetAddress;

    @JsonProperty("city")
    public String city;

    @JsonProperty("postalCode")
    public int postalCode;

    @JsonProperty("country")
    public String country;

    @JsonProperty("phone")
    public String phone;

    @JsonProperty("fax")
    public String fax;
}
