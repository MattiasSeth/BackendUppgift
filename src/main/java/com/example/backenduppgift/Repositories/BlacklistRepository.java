package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Blacklist findByExternalId(int id); //
}
