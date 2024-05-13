package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.ContractCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractCustomerRepository extends JpaRepository<ContractCustomer, Long> {
    ContractCustomer findByExternalId(int id);
}
