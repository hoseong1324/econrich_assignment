package com.portfolio.server.locations.entity;

import com.portfolio.server.countries.entity.Countries;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name ="locations")
public class Locations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;
    @Column(length = 40)
    private String streetAddress;
    @Column(length = 12)
    private String postalCode;
    @Column(length = 30)
    private String city;
    @Column(length = 25)
    private String stateProvince;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Countries countries;
}
