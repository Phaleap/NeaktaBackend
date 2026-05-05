package com.leap.neakta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_en", nullable = false, length = 50)
    private String nameEn;

    @Column(name = "name_km", nullable = false, length = 50)
    private String nameKh;

    @Column(length = 50)
    private String icon;

    @Column(name = "color_hex", length = 7)
    private String colorHex;
}
