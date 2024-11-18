package com.econrich.assignment.locations.entity;

import com.econrich.assignment.countries.entity.Countries;
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
    private Countries countryId;
}
