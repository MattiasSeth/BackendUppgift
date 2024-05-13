package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.Entities.ContractCustomer;
import com.example.backenduppgift.Repositories.ContractCustomerRepository;
import com.example.backenduppgift.Services.ContractCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractCustomerServiceImpl implements ContractCustomerService {

    private final ContractCustomerRepository contractCustomerRepository;
    @Override
    public ContractCustomer customerXMLDtoToCustomer(ContractCustomerDTO contractCustomerDTO) {
        return ContractCustomer.builder()
                .externalId(contractCustomerDTO.getExternalId())
                .companyName(contractCustomerDTO.getCompanyName())
                .contactName(contractCustomerDTO.getContactName())
                .contactTitle(contractCustomerDTO.getContactTitle())
                .streetAddress(contractCustomerDTO.getStreetAddress())
                .city(contractCustomerDTO.getCity())
                .postalCode(contractCustomerDTO.getPostalCode())
                .country(contractCustomerDTO.getCountry())
                .phone(contractCustomerDTO.getPhone())
                .fax(contractCustomerDTO.getFax())
                .build();
    }


    @Override
    public void addCustomer(ContractCustomer contractCustomer) {
        contractCustomerRepository.save(contractCustomer);
    }

    @Override
    public boolean existsCustomerXML(ContractCustomer contractCustomer) {
        int customerXMLExternalId = contractCustomer.getExternalId();
        contractCustomerRepository.findByExternalId(customerXMLExternalId);
        Optional<ContractCustomer> customerXMLOptional = Optional.ofNullable(contractCustomerRepository.findByExternalId(customerXMLExternalId));
        return customerXMLOptional.isPresent();
    }
}
