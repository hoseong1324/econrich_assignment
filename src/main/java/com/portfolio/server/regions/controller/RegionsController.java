package com.portfolio.server.regions.controller;

import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
import com.portfolio.server.regions.service.GetRegionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
@Tag(name = "003.지역", description = "지역 API")
public class RegionsController {

    private final ResponseService responseService;
    private final GetRegionsService getRegionsService;

    @GetMapping
    public CommonResult getRegions() {
        return responseService.getListResult(getRegionsService.executeForList());
    }
}
