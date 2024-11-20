package com.econrich.assignment.employees.service;

import com.econrich.assignment.common.exception.CustomException;
import com.econrich.assignment.common.exception.ExceptionCode;
import com.econrich.assignment.employees.dto.EmployeesDto;
import com.econrich.assignment.employees.entity.Employees;
import com.econrich.assignment.employees.repo.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetEmployeeService {

    private final EmployeesRepository employeesRepository;

    @Transactional(readOnly = true)
    public List<EmployeesDto.EmployeesSummary> executeForList(){
        return employeesRepository.findAll().stream().map(
                employees -> EmployeesDto.EmployeesSummary.builder()
                        .employeeId(employees.getEmployeeId())
                        .firstName(employees.getFirstName())
                        .lastName(employees.getLastName())
                        .email(employees.getEmail())
                        .phoneNumber(employees.getPhoneNumber())
                        .hireDate(employees.getHireDate())
                        .jobId(employees.getJobs().getJobId())
                        .salary(employees.getSalary())
                        .commissionPct(employees.getCommissionPct())
                        .managerId(employees.getManager() != null ? employees.getManager().getEmployeeId() : null)
                        .departmentId(employees.getDepartments().getDepartmentId())
                        .build()
        ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeesDto.EmployeesSummary execute(int employeeId){
        Employees employees = employeesRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new CustomException(ExceptionCode.EMPLOYEES_NOT_FOUND));
        return EmployeesDto.EmployeesSummary.builder()
                .employeeId(employees.getEmployeeId())
                .firstName(employees.getFirstName())
                .lastName(employees.getLastName())
                .email(employees.getEmail())
                .phoneNumber(employees.getPhoneNumber())
                .hireDate(employees.getHireDate())
                .jobId(employees.getJobs().getJobId())
                .salary(employees.getSalary())
                .commissionPct(employees.getCommissionPct())
                .managerId(employees.getManager().getEmployeeId())
                .departmentId(employees.getEmployeeId())
                .build();
    }
}
