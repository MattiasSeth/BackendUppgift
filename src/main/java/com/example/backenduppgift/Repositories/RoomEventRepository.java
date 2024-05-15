package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.RoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomEventRepository extends JpaRepository<RoomEvent, Long> {
}
