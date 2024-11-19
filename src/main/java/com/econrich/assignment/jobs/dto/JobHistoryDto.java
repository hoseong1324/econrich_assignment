package com.econrich.assignment.jobs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

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
    public static class UpdateJobs{
        @NotBlank(message = "필수 항목이 누락되었습니다.")
        private String jobId;
        @Size(max = 35)
        private String jobTitle;
        private Integer minSalary;
        private Integer maxSalary;
    }

    @Getter
    public static class UpdateSalaryByPercent{
        @NotBlank
        private String jobId;
        private Integer minSalaryPercent;
        private Integer maxSalaryPercent;
    }
}
