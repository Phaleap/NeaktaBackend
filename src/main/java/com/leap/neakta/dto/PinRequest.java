package com.leap.neakta.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PinRequest {
    private Integer provinceId;
    private Integer categoryId;
    private String title;
    private String story;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
}