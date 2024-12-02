package com.portfolio.server.regions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name ="regions")
public class Regions {
    @Id
    private int regionId;
    private String regionName;
}
