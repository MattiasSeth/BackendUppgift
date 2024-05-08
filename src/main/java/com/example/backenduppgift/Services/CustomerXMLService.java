package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.CustomerListXMLDto;
import com.example.backenduppgift.DTO.CustomerXMLDto;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.CustomerXML;
import com.example.backenduppgift.Entities.Shipper;

public interface CustomerXMLService {

    public CustomerXML CustomerXMLDtoToCustomer(CustomerXMLDto customerXMLDto);

    public void addCustomer(CustomerXML customerXML);

    public boolean existsCustomerXML (CustomerXML customerXML);
}
