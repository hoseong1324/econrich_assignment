package com.econrich.assignment.jobs.service;

import com.econrich.assignment.common.exception.CustomException;
import com.econrich.assignment.common.exception.CustomUniversalException;
import com.econrich.assignment.common.exception.ExceptionCode;
import com.econrich.assignment.jobs.dto.JobHistoryDto;
import com.econrich.assignment.jobs.entity.Jobs;
import com.econrich.assignment.jobs.repo.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PatchJobsService {
    private final JobsRepository jobsRepository;

    @Transactional
    public void execute(JobHistoryDto.UpdateJobs form) {
        Jobs jobs = jobsRepository.findById(form.getJobId())
                .orElseThrow(() -> new CustomException(ExceptionCode.JOBS_NOT_FOUND));

        updateJobs(jobs, form);
    }

    @Transactional
    public void executeForPercentSalary(JobHistoryDto.UpdateSalaryByPercent form){
        Jobs jobs = jobsRepository.findById(form.getJobId())
                .orElseThrow(() -> new CustomException(ExceptionCode.JOBS_NOT_FOUND));

        if(form.getMinSalaryPercent() != null){
            double minSalary = jobs.getMinSalary().doubleValue();
            double percentValue =  minSalary * form.getMinSalaryPercent().doubleValue() / 100.0;
            long impressionValue = Math.round(minSalary + percentValue);
            if(jobs.getMaxSalary().compareTo(BigDecimal.valueOf(impressionValue)) < 0){
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST,"최소 급여의 인상 금액이 최대 급여보다 높을 수 없습니다.");
            }
            jobs.setMinSalary(BigDecimal.valueOf(impressionValue));
        }
        if(form.getMaxSalaryPercent() != null){
            double maxSalary = jobs.getMaxSalary().doubleValue();
            double percentValue =  maxSalary * form.getMaxSalaryPercent().doubleValue() / 100.0;
            jobs.setMaxSalary(BigDecimal.valueOf(Math.round(maxSalary + percentValue)));
        }
    }

    @Transactional
    protected void updateJobs(Jobs jobs, JobHistoryDto.UpdateJobs form) {
        if (form.getJobTitle() != null) jobs.setJobTitle(form.getJobTitle());

        if(form.getMinSalary() != null && form.getMaxSalary() != null){
            if(form.getMinSalary() > form.getMaxSalary()){
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST, "최소 급여는 최대 급여를 초과 할 수 없습니다.");
            }
            if(String.valueOf(form.getMinSalary()).length() > 8 || String.valueOf(form.getMaxSalary()).length() > 8){
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST, "급여 입력치는 9,999,999원을 초과 할 수 없습니다.");
            }
        }

        if (form.getMinSalary() != null && form.getMaxSalary() == null) {
            if(BigDecimal.valueOf(form.getMinSalary()).compareTo(jobs.getMaxSalary()) > 0){
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST, "최소 급여는 최대 급여를 초과 할 수 없습니다.");
            }
            if(String.valueOf(form.getMinSalary()).length() > 8) {
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST, "최소 급여는 9,999,999원을 초과 할 수 없습니다.");
            }
            jobs.setMinSalary(BigDecimal.valueOf(form.getMinSalary()));
        } else if (form.getMaxSalary() != null) {
            if(String.valueOf(form.getMaxSalary()).length() > 8) {
                throw new CustomUniversalException(HttpStatus.BAD_REQUEST, "최대 급여는 9,999,999원을 초과 할 수 없습니다.");
            }
            jobs.setMaxSalary(BigDecimal.valueOf(form.getMaxSalary()));
        }
    }
}
