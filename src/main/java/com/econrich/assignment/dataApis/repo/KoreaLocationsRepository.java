package com.econrich.assignment.dataApis.repo;

import com.econrich.assignment.dataApis.entity.KoreaLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoreaLocationsRepository extends JpaRepository<KoreaLocations, Integer> {


}
