package com.leap.neakta.controller;

import com.leap.neakta.entity.ProvinceStats;
import com.leap.neakta.service.ProvinceStatsService;  // ← this import
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class ProvinceStatsController {

    private final ProvinceStatsService provinceStatsService;

    @GetMapping("/leaderboard")
    public ResponseEntity<List<ProvinceStats>> getLeaderboard() {
        return ResponseEntity.ok(provinceStatsService.getLeaderboard());
    }

    @PostMapping("/recalculate")
    public ResponseEntity<Map<String, String>> recalculate() {
        provinceStatsService.recalculateStats();
        return ResponseEntity.ok(Map.of("message", "Stats recalculated"));
    }
}