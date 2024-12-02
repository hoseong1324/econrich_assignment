package com.portfolio.server.countries.service;

import com.portfolio.server.countries.dto.CountriesDto;
import com.portfolio.server.countries.repo.CountriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetCountriesService {

    private final CountriesRepository countriesRepository;

    @Transactional(readOnly = true)
    public List<CountriesDto.CountriesSummary> executeForList(){
        return countriesRepository.findAll().stream().map(countries ->
                CountriesDto.CountriesSummary.builder()
                        .countryId(countries.getCountryId())
                        .countryName(countries.getCountryName())
                        .regionId(countries.getRegions().getRegionId())
                        .build()
        ).collect(Collectors.toList());
    }
}
