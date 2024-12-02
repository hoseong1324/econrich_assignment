package com.portfolio.server.employees.entity;

import com.portfolio.server.departments.entity.Departments;
import com.portfolio.server.jobs.entity.JobHistory;
import com.portfolio.server.jobs.entity.Jobs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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
    private Jobs jobs;
    @Column(precision = 8, scale = 2)
    private BigDecimal salary;
    @Column(precision = 2, scale = 2)
    private BigDecimal commissionPct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employees manager;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Departments departments;
    @OneToMany(mappedBy = "embeddableJobHistory.employees")
    private List<JobHistory> jobHistory;

}
