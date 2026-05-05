package com.leap.neakta.controller;

import com.leap.neakta.dto.PinResponse;
import com.leap.neakta.service.SavedPinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
public class SavedPinController {

    private final SavedPinService savedPinService;

    @PostMapping("/{pinId}")
    public ResponseEntity<Map<String, String>> toggleSave(@PathVariable UUID pinId) {
        String result = savedPinService.toggleSave(pinId);
        return ResponseEntity.ok(Map.of("message", result));
    }

    @GetMapping
    public ResponseEntity<List<PinResponse>> getMySavedPins() {
        return ResponseEntity.ok(savedPinService.getMySavedPins());
    }
}