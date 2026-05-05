package com.leap.neakta.repository;

import com.leap.neakta.entity.ProvinceStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProvinceStatsRepository extends JpaRepository<ProvinceStats, Integer> {
    Optional<ProvinceStats> findByProvinceId(Integer provinceId);

    @Query("SELECT ps FROM ProvinceStats ps ORDER BY ps.totalPins DESC")
    List<ProvinceStats> findAllOrderByRank();
}