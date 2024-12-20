package com.portfolio.server.departments.service;

import com.portfolio.server.common.exception.CustomException;
import com.portfolio.server.common.exception.ExceptionCode;
import com.portfolio.server.departments.dto.DepartmentsDto;
import com.portfolio.server.departments.entity.Departments;
import com.portfolio.server.departments.repo.DepartmentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDepartmentsService {
    private final DepartmentsRepository departmentsRepository;

    public List<DepartmentsDto.DepartmentsSummary> executeForList() {
        return departmentsRepository.findAll().stream().map(departments ->
                DepartmentsDto.DepartmentsSummary.builder()
                        .departmentId(departments.getDepartmentId())
                        .departmentName(departments.getDepartmentName())
                        .managerId(departments.getEmployees() != null ? departments.getEmployees().getEmployeeId() : null)
                        .locationId(departments.getLocations().getLocationId())
                        .build()
        ).collect(Collectors.toList());
    }

    public DepartmentsDto.DepartmentsSummary execute(int departmentId) {
        Departments departments = departmentsRepository.findById(departmentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.DEPARTMENTS_NOT_FOUND));
        return DepartmentsDto.DepartmentsSummary.builder()
                .departmentId(departments.getDepartmentId())
                .departmentName(departments.getDepartmentName())
                .managerId(departments.getEmployees() != null ? departments.getEmployees().getEmployeeId() : null)
                .locationId(departments.getLocations().getLocationId())
                .build();
    }
}
