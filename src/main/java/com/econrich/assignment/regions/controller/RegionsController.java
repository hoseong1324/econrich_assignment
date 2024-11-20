package com.econrich.assignment.regions.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.regions.service.GetRegionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class RegionsController {

    private final ResponseService responseService;
    private final GetRegionsService getRegionsService;

    @GetMapping
    public CommonResult getRegions() {
        return responseService.getListResult(getRegionsService.executeForList());
    }
}
