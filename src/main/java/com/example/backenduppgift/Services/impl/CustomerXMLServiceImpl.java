package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.CustomerListXMLDto;
import com.example.backenduppgift.DTO.CustomerXMLDto;
import com.example.backenduppgift.Entities.CustomerXML;
import com.example.backenduppgift.Entities.Shipper;
import com.example.backenduppgift.Repositories.CustomerXMLRepository;
import com.example.backenduppgift.Repositories.ShipperRepository;
import com.example.backenduppgift.Services.CustomerXMLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerXMLServiceImpl implements CustomerXMLService {

    private final CustomerXMLRepository customerXMLRepository;
    @Override
    public CustomerXML customerXMLDtoToCustomer(CustomerXMLDto customerXMLDto) {
        return CustomerXML.builder()
                .externalId(customerXMLDto.getExternalId())
                .companyName(customerXMLDto.getCompanyName())
                .contactName(customerXMLDto.getContactName())
                .contactTitle(customerXMLDto.getContactTitle())
                .streetAddress(customerXMLDto.getStreetAddress())
                .city(customerXMLDto.getCity())
                .postalCode(customerXMLDto.getPostalCode())
                .country(customerXMLDto.getCountry())
                .phone(customerXMLDto.getPhone())
                .fax(customerXMLDto.getFax())
                .build();
    }


    @Override
    public void addCustomer(CustomerXML customerXML) {
        customerXMLRepository.save(customerXML);
    }

    @Override
    public boolean existsCustomerXML(CustomerXML customerXML) {
        int customerXMLExternalId = customerXML.getExternalId();
        customerXMLRepository.findByExternalId(customerXMLExternalId);
        Optional<CustomerXML> customerXMLOptional = Optional.ofNullable(customerXMLRepository.findByExternalId(customerXMLExternalId));
        return customerXMLOptional.isPresent();
    }
}
