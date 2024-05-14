package com.example.backenduppgift.Repositories;

import com.example.backenduppgift.Entities.Queue;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QueueRepository extends CrudRepository<Queue, UUID> {
}
