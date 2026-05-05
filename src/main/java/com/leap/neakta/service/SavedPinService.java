package com.leap.neakta.service;

import com.leap.neakta.dto.PinResponse;
import com.leap.neakta.entity.*;
import com.leap.neakta.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedPinService {

    private final SavedPinRepository savedPinRepository;
    private final PinRepository pinRepository;
    private final UserRepository userRepository;
    private final PinService pinService;

    @Transactional
    public String toggleSave(UUID pinId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new RuntimeException("Pin not found"));

        // Toggle: if already saved → unsave
        if (savedPinRepository.existsByUserIdAndPinId(user.getId(), pinId)) {
            savedPinRepository.deleteByUserIdAndPinId(user.getId(), pinId);
            return "Pin unsaved";
        }

        SavedPin savedPin = new SavedPin();
        savedPin.setUser(user);
        savedPin.setPin(pin);
        savedPinRepository.save(savedPin);
        return "Pin saved";
    }

    public List<PinResponse> getMySavedPins() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return savedPinRepository.findByUserId(user.getId())
                .stream()
                .map(saved -> pinService.getPinById(saved.getPin().getId()))
                .collect(Collectors.toList());
    }
}