package com.econrich.assignment.regions.dto;

import lombok.Builder;
import lombok.Getter;

public class RegionsDto {

    @Getter
    @Builder
    public static class RegionsSummary{
        private int regionId;
        private String regionName;
    }
}
