package com.econrich.assignment.jobs.service;

import com.econrich.assignment.jobs.dto.JobHistoryDto;
import com.econrich.assignment.jobs.repo.JobHistoryRepository;
import com.econrich.assignment.jobs.repo.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetJobsService {
    private final JobsRepository jobsRepository;
    private final JobHistoryRepository jobHistoryRepository;

    @Transactional(readOnly = true)
    public List<JobHistoryDto.JobHistorySummary> historyExecute(int employeeId) {
        return jobHistoryRepository.findAllByEmployeeId(employeeId).stream().map(
                history -> JobHistoryDto.JobHistorySummary.builder()
                        .employeeId(history.getEmbeddableJobHistory().getEmployees().getEmployeeId())
                        .startDate(history.getEmbeddableJobHistory().getStartDate())
                        .endDate(history.getEndDate())
                        .jobId(history.getJobs().getJobId())
                        .departmentId(history.getDepartments().getDepartmentId())
                        .build()
        ).sorted(Comparator.comparing(JobHistoryDto.JobHistorySummary::getEndDate).reversed()).collect(Collectors.toList());
    }
}
