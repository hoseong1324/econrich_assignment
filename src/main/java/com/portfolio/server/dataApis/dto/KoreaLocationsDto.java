package com.portfolio.server.dataApis.dto;


import lombok.Getter;
import lombok.Setter;

public class KoreaLocationsDto {

    @Getter
    @Setter
    public static class LocationsCondition{
        private String si;
        private String gu;
        private String dong;
    }
}
