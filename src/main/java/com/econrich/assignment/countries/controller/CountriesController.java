package com.econrich.assignment.countries.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.countries.service.GetCountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountriesController {

    private final ResponseService responseService;
    private final GetCountriesService getCountriesService;

    @GetMapping
    public CommonResult getCountries(){
        return responseService.getListResult(getCountriesService.executeForList());
    }

    
}
