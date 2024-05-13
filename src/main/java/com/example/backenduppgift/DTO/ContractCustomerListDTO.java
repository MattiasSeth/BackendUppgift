package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractCustomerListDTO {
    @JacksonXmlProperty(localName = "customers")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<ContractCustomerDTO> contractCustomerDTOList;
}
