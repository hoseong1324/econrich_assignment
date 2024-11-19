package com.econrich.assignment.locations.dto;

import lombok.Builder;
import lombok.Getter;

public class LocationsDto {

    @Builder
    @Getter
    public static class LocationsSummary{
        private int locationId;
        private String streetAddress;
        private String postalCode;
        private String city;
        private String stateProvince;
        private String countryId;
    }
}
