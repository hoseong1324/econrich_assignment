package com.econrich.assignment.employees.entity;

import com.econrich.assignment.departments.entity.Departments;
import com.econrich.assignment.jobs.entity.Jobs;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Table(name ="employees")
public class Employees {
    @Id
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate hireDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs jobId;
    @Column(precision = 8, scale = 2)
    private BigDecimal salary;
    @Column(precision = 2, scale = 2)
    private BigDecimal commissionPct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employees managerId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Departments departmentId;
}
