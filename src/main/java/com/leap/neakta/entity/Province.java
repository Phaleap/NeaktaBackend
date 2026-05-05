package com.leap.neakta.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_en", nullable = false, length = 80)
    private String nameEn;

    @Column(name = "name_km", nullable = false, length = 80)
    private String nameKm;

    @Column(length = 10)
    private String region;

    @Column(name = "center_lat", precision = 9, scale = 6)
    private java.math.BigDecimal centerLat;

    @Column(name = "center_lng", precision = 9, scale = 6)
    private java.math.BigDecimal centerLng;
}
