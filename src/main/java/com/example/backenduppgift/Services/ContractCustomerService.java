package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.Entities.ContractCustomer;

public interface ContractCustomerService {

    public ContractCustomer customerXMLDtoToCustomer(ContractCustomerDTO contractCustomerDTO);

    public void addCustomer(ContractCustomer contractCustomer);

    public boolean existsCustomerXML (ContractCustomer contractCustomer);
}
