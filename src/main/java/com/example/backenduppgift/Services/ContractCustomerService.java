package com.example.backenduppgift.Services;

import com.example.backenduppgift.DTO.ContractCustomerDTO;
import com.example.backenduppgift.DTO.ShipperDto;
import com.example.backenduppgift.Entities.ContractCustomer;

import java.util.List;

public interface ContractCustomerService {

    public ContractCustomer customerXMLDtoToCustomer(ContractCustomerDTO contractCustomerDTO);

    public void addCustomer(ContractCustomer contractCustomer);

    public boolean existsCustomerXML (ContractCustomer contractCustomer);

    public List<ContractCustomerDTO> getAllContractCustomers();
}
