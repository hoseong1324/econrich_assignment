package com.econrich.assignment.jobs.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class JobHistoryDto {

    @Builder
    @Getter
    public static class JobHistorySummary{
        private int employeeId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String jobId;
        private int departmentId;
    }

    @Getter
    @Setter
    public static class UpdateJobs{
        @Size(max = 35)
        private String jobTitle;
        private Integer minSalary;
        private Integer maxSalary;
    }

    @Getter
    @Setter
    public static class UpdateSalaryByPercent{
        private Integer minSalaryPercent;
        private Integer maxSalaryPercent;
    }
}
