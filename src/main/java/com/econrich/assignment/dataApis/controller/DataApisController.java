package com.econrich.assignment.dataApis.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.dataApis.service.GetWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dataApis")
@RequiredArgsConstructor
@RestController
public class DataApisController {

    private final ResponseService responseService;
    private final GetWeatherService getWeatherService;

    @GetMapping("/weather")
    public CommonResult getWeather(String si, String gu, String dong) {
        return responseService.getSingleResult(getWeatherService.execute(si, gu, dong));
    }

}
