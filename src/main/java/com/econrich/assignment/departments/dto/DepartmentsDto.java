package com.econrich.assignment.departments.dto;

import lombok.Builder;
import lombok.Getter;

public class DepartmentsDto {
    @Builder
    @Getter
    public static class DepartmentsSummary{
        private int departmentId;
        private String departmentName;
        private Integer managerId;
        private int locationId;
    }
}
