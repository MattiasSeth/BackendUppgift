package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.CustomerXML;
import com.example.backenduppgift.Entities.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerXMLRepository extends JpaRepository<CustomerXML, Long> {
    CustomerXML findByExternalId(int id);
}
