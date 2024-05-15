package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.RoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QueueRepository extends JpaRepository<RoomEvent, Long> {
}
