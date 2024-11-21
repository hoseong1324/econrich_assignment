package com.econrich.assignment.dataApis.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.dataApis.service.GetWeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dataApis")
@RequiredArgsConstructor
@RestController
@Tag(name = "007.공공데이터", description = "공공데이터 활용 API")
public class DataApisController {

    private final ResponseService responseService;
    private final GetWeatherService getWeatherService;

    /**
     * 공공데이터 출처 지역 DB 조회 후 날씨 조회
     * @param si
     * @param gu
     * @param dong
     * @return
     */
    @GetMapping("/weather")
    public CommonResult getWeather(String si, String gu, String dong) {
        return responseService.getSingleResult(getWeatherService.execute(si, gu, dong));
    }

}
