package com.leap.neakta.controller;

import com.leap.neakta.dto.PinRequest;
import com.leap.neakta.dto.PinResponse;
import com.leap.neakta.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pins")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @PostMapping
    public ResponseEntity<PinResponse> createPin(@RequestBody PinRequest request) {
        return ResponseEntity.ok(pinService.createPin(request));
    }

    @GetMapping
    public ResponseEntity<List<PinResponse>> getAllPins() {
        return ResponseEntity.ok(pinService.getAllPins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PinResponse> getPin(@PathVariable UUID id) {
        return ResponseEntity.ok(pinService.getPinById(id));
    }

    @GetMapping("/province/{provinceId}")
    public ResponseEntity<List<PinResponse>> getPinsByProvince(
            @PathVariable Integer provinceId) {
        return ResponseEntity.ok(pinService.getPinsByProvince(provinceId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PinResponse> updatePin(@PathVariable UUID id,
                                                 @RequestBody PinRequest request) {
        return ResponseEntity.ok(pinService.updatePin(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePin(@PathVariable UUID id) {
        pinService.deletePin(id);
        return ResponseEntity.noContent().build();
    }
}