package com.econrich.assignment.countries.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.common.service.SlackSendService;
import com.econrich.assignment.countries.service.GetCountriesService;
import com.slack.api.methods.SlackApiException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
