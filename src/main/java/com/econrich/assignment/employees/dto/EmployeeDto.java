package com.econrich.assignment.employees.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDto {

    @Builder
    @Getter
    public static class Summary{
        private int employeeId;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private LocalDate hireDate;
        private int jobId;
        private BigDecimal salary;
        private BigDecimal commissionPct;
        private Integer managerId;
        private int departmentId;
    }
}
