package com.portfolio.server.jobs.entity;

import com.portfolio.server.departments.entity.Departments;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "job_history")
@Getter
public class JobHistory {

    @EmbeddedId
    private EmbeddableJobHistory embeddableJobHistory;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs jobs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Departments departments;
}
