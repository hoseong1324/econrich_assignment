package com.econrich.assignment.jobs.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.jobs.dto.JobHistoryDto;
import com.econrich.assignment.jobs.service.GetJobsService;
import com.econrich.assignment.jobs.service.PatchJobsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Tag(name = "004.직업", description = "직업 API")
public class JobsController {
    private final ResponseService responseService;
    private final GetJobsService getJobsService;
    private final PatchJobsService patchJobsService;


    @GetMapping("/history/{employeeId}")
    public CommonResult getJobsHistory(@PathVariable int employeeId) {
        return responseService.getListResult(getJobsService.executeForHistory(employeeId));
    }

    @PatchMapping(value = "/{jobId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult updateSalary(@PathVariable String jobId, @Valid @RequestBody JobHistoryDto.UpdateJobs form){
        patchJobsService.execute(jobId, form);
        return responseService.getSuccessResult();
    }

    @PatchMapping(value = "/{jobId}/salary", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult updateSalaryByPercent(@PathVariable String jobId, @RequestBody JobHistoryDto.UpdateSalaryByPercent form){
        patchJobsService.executeForPercentSalary(jobId, form);
        return responseService.getSuccessResult();
    }
}
