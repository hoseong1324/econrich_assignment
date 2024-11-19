package com.econrich.assignment.jobs.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.jobs.dto.JobHistoryDto;
import com.econrich.assignment.jobs.service.GetJobsService;
import com.econrich.assignment.jobs.service.PatchJobsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobsController {
    private final ResponseService responseService;
    private final GetJobsService getJobsService;
    private final PatchJobsService patchJobsService;

    @GetMapping("/history/{employeeId}")
    public CommonResult getJobsHistory(@PathVariable int employeeId) {
        return responseService.getListResult(getJobsService.historyExecute(employeeId));
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult updateSalary(@Valid @RequestBody JobHistoryDto.UpdateJobs form){
        patchJobsService.execute(form);
        return responseService.getSuccessResult();
    }

    @PatchMapping(value = "/salary", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult updateSalaryByPercent(@RequestBody JobHistoryDto.UpdateSalaryByPercent form){
        patchJobsService.executeForPercentSalary(form);
        return responseService.getSuccessResult();
    }
}
