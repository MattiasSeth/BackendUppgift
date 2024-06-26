package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.Customer;
import com.example.backenduppgift.Entities.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Shipper findByExternalId(int id);
}
