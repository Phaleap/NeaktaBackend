package com.leap.neakta.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "province_stats")
public class ProvinceStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", unique = true, nullable = false)
    private Province province;

    @Column(name = "total_pins")
    private int totalPins = 0;

    @Column(name = "total_upvotes")
    private int totalUpvotes = 0;

    @Column(name = "total_contributors")
    private int totalContributors = 0;

    private Integer rank;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}
