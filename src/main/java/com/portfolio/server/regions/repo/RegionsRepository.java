package com.portfolio.server.regions.repo;

import com.portfolio.server.regions.entity.Regions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionsRepository extends JpaRepository<Regions, Integer> {
}
