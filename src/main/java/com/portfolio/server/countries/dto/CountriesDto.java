package com.portfolio.server.countries.dto;

import lombok.Builder;
import lombok.Getter;

public class CountriesDto {

    @Getter
    @Builder
    public static class CountriesSummary{
        private String countryId;
        private String countryName;
        private int regionId;
    }
}
