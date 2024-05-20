package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.ContractCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractCustomerRepository extends JpaRepository<ContractCustomer, Long> {
    ContractCustomer findByExternalId(int id);
    List<ContractCustomer> findAll(Sort sort);
    Page<ContractCustomer> findAll(Pageable pageable);
}
