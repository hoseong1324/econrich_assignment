package com.econrich.assignment.departments.entity;

import com.econrich.assignment.employees.entity.Employees;
import com.econrich.assignment.locations.entity.Locations;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name ="departments")
public class Departments {
    @Id
    private int departmentId;
    @Column(length = 30)
    private String departmentName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "manager_id")
    private Employees employees;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Locations locations;
}
