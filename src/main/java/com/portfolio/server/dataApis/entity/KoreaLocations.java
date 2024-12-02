package com.portfolio.server.dataApis.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "korea_locations")
public class KoreaLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int koreaLocationsId;
    private String si;
    private String gu;
    private String dong;
    @Column(name = "grid_x")
    private int gridX;
    @Column(name = "grid_y")
    private int gridY;
}
