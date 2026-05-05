package com.leap.neakta.repository;

import com.leap.neakta.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PinRepository extends JpaRepository<Pin, UUID> {
    List<Pin> findByProvincedId(Integer provincedId);
    List<Pin> findByCategoryId(Integer categoryId);
    List<Pin> findByUserId(UUID userId);
    List<Pin> findByStatus(String status);

    @Query("SELECT p FROM Pin p WHERE p.status = 'ACTIVE' ORDER BY p.score DESC")
    List<Pin> findTopPins();
}
