package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.RoomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomEventRepository extends JpaRepository<RoomEvent, Long> {
    List<RoomEvent> findRoomEventByRoomId(Long roomId);
}
