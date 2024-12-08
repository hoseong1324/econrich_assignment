package com.portfolio.server.dataApis.controller;

import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
import com.portfolio.server.dataApis.service.GetWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/apis/dataApis")
@RequiredArgsConstructor
@RestController
@Tag(name = "007.공공데이터", description = "공공데이터 활용 API")
public class DataApisController {

    private final ResponseService responseService;
    private final GetWeatherService getWeatherService;

    /**
     * 공공데이터 출처 지역 DB 조회 후 날씨 조회
     * @return
     */
    @GetMapping("/weather")
    @Operation(summary = "해당 지역 현 시간 날씨 조회", description = "지역의 시(si), 구(gu), 동(로)(dong)를 조회하여 해당 경도값으로 공공데이터에서 현 날씨 상황을 받아옵니다.")
    public CommonResult getWeather(String si, String gu, String dong) {
        return responseService.getSingleResult(getWeatherService.execute(si, gu, dong));
    }

}
