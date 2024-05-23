package com.example.backenduppgift.Services.impl;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.Entities.ContractCustomer;
import com.example.backenduppgift.Repositories.ContractCustomerRepository;
import com.example.backenduppgift.Services.ContractCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ContractCustomer> searchCompanies(String searchWord, Sort sort) {
        String toLowerCaseSearchWord = searchWord.toLowerCase();
        List<ContractCustomer> companyClients = contractCustomerRepository.findAll(sort); // sorterar sökt kunder
        List<ContractCustomer> companyClientHits = companyClients.stream()

                .filter(cc -> cc.getCompanyName().toLowerCase().contains(toLowerCaseSearchWord) ||
                        cc.getContactName().toLowerCase().contains(toLowerCaseSearchWord) ||
                        cc.getCity().toLowerCase().contains(toLowerCaseSearchWord) ||
                        cc.getCountry().toLowerCase().contains(toLowerCaseSearchWord))
                .collect(Collectors.toList());
        return companyClientHits;    }

    @Override
    public List<ContractCustomerDTO> getAllContractCustomers() {
        return contractCustomerRepository.findAll().stream().map(r -> contractCustomerToContractCustomerDto(r)).toList();
    }

    @Override
    public ContractCustomer getCustomerById(Long id) {
        ContractCustomer contractCustomer = contractCustomerRepository.findByExternalId(Math.toIntExact(id));
        return contractCustomer;
    }

    private ContractCustomerDTO contractCustomerToContractCustomerDto(ContractCustomer contractCustomer) {
        return ContractCustomerDTO.builder().companyName(contractCustomer.getCompanyName())
                .contactName(contractCustomer.getContactName())
                .country(contractCustomer.getCountry())
                .contactTitle(contractCustomer.getContactTitle()).build();
    }
}
