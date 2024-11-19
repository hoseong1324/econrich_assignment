package com.econrich.assignment.employees.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeesDto {

    @Builder
    @Getter
    public static class EmployeesSummary{
        private int employeeId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private LocalDate hireDate;
        private String jobId;
        private BigDecimal salary;
        private BigDecimal commissionPct;
        private Integer managerId;
        private int departmentId;
    }

    @Getter
    @Setter
    public static class UpdateForm{
        @Size(max = 20)
        private String firstName;
        @Size(max = 25)
        private String lastName;
        @Size(max = 20)
        private String email;
        private String phoneNumber;
        private LocalDate hireDate;
        private String jobId;
        private Integer salary;
        private Integer commissionPct;
        private Integer managerId;
        private Integer departmentId;
    }
}
