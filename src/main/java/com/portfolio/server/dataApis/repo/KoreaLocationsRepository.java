package com.portfolio.server.dataApis.repo;

import com.portfolio.server.dataApis.entity.KoreaLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoreaLocationsRepository extends JpaRepository<KoreaLocations, Integer> {


}
