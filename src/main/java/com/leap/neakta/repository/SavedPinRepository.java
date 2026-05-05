package com.leap.neakta.repository;

import com.leap.neakta.entity.SavedPin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SavedPinRepository extends JpaRepository<SavedPin, UUID> {
    List<SavedPin> findByUserId(UUID userId);
    boolean existsByUserIdAndPinId(UUID userId, UUID pinId);
    void deleteByUserIdAndPinId(UUID userId, UUID pinId);
}