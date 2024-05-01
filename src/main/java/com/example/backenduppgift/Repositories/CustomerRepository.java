package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
Customer findByName(String name);
}
