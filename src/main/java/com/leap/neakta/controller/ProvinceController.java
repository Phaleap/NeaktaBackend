package com.leap.neakta.controller;

import com.leap.neakta.entity.Province;
import com.leap.neakta.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceRepository provinceRepository;

    @GetMapping
    public ResponseEntity<List<Province>> getAllProvinces() {
        return ResponseEntity.ok(provinceRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> getProvince(@PathVariable Integer id) {
        return ResponseEntity.ok(
                provinceRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Province not found"))
        );
    }
}