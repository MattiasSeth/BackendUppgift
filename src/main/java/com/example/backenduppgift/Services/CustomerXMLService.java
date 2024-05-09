package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerXMLDto;
import com.example.backenduppgift.Entities.CustomerXML;

public interface CustomerXMLService {

    public CustomerXML customerXMLDtoToCustomer(CustomerXMLDto customerXMLDto);

    public void addCustomer(CustomerXML customerXML);

    public boolean existsCustomerXML (CustomerXML customerXML);
}
