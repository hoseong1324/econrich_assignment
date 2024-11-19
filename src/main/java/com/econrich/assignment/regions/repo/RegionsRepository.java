package com.econrich.assignment.regions.repo;

import com.econrich.assignment.regions.entity.Regions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionsRepository extends JpaRepository<Regions, Integer> {
}
