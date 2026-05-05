package com.leap.neakta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PinRequest {
    private Integer provinceId;
    private Integer categoryId;
    @NotBlank
    private String title;
    private String story;
    private String address;
    @NotNull
    private BigDecimal lat;
    @NotNull private BigDecimal lng;
}