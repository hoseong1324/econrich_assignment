package com.portfolio.server.countries.controller;

import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
import com.portfolio.server.countries.service.GetCountriesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
@Tag(name = "001.국가", description = "국가 API")
public class CountriesController {

    private final ResponseService responseService;
    private final GetCountriesService getCountriesService;

    @GetMapping
    public CommonResult getCountries(){
        return responseService.getListResult(getCountriesService.executeForList());
    }

    
}
