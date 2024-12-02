package com.portfolio.server.countries.entity;

import com.portfolio.server.regions.entity.Regions;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "countries")
public class Countries {
    @Id
    @Column(columnDefinition = "char(2)")
    private String countryId;
    @Column(length = 40)
    private String countryName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Regions regions;
}
