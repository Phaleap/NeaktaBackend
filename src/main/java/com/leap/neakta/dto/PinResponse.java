package com.leap.neakta.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PinResponse {
    private UUID id;
    private String title;
    private String story;
    private String address;
    private BigDecimal lat;
    private BigDecimal lng;
    private int score;
    private int upvoteCount;
    private String status;
    private boolean isVerified;
    private LocalDateTime createdAt;

    // Nested info
    private String authorUsername;
    private String provinceName;
    private String categoryName;
    private String categoryIcon;
    private String categoryColor;
}