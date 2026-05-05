package com.leap.neakta.repository;

import com.leap.neakta.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    Optional<Vote> findByPinIdAndUserId(UUID pinId, UUID userId);
    boolean existsByPinIdAndUserId(UUID pinId, UUID userId);
}