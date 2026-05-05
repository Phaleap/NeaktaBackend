package com.leap.neakta.controller;

import com.leap.neakta.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{pinId}")
    public ResponseEntity<Map<String, String>> vote(
            @PathVariable UUID pinId,
            @RequestParam String type) {
        String result = voteService.vote(pinId, type);
        return ResponseEntity.ok(Map.of("message", result));
    }
}