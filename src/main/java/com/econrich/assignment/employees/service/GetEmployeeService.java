package com.econrich.assignment.employees.service;

import com.econrich.assignment.employees.dto.EmployeeDto;
import com.econrich.assignment.employees.entity.Employees;
import com.econrich.assignment.employees.repo.EmployeesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetEmployeeService {

    private final EmployeesRepository employeesRepository;

    public List<EmployeeDto.Summary> getEmplyeesList(){
        return employeesRepository.findAll().stream().map(
                employees -> EmployeeDto.Summary.builder()
                        .employeeId(employees.getEmployeeId())
                        .firstName(employees.getFirstName())
                        .lastName(employees.getLastName())
                        .email(employees.getEmail())
                        .phoneNumber(employees.getPhoneNumber())
                        .hireDate(employees.getHireDate())
                        .jobId(employees.getEmployeeId())
                        .salary(employees.getSalary())
                        .commissionPct(employees.getCommissionPct())
                        .managerId(employees.getManagerId() != null ? employees.getManagerId().getEmployeeId() : null)
                        .departmentId(employees.getEmployeeId())
                        .build()
        ).collect(Collectors.toList());
    }

    public EmployeeDto.Summary getEmployee(Long employeeId){
        Employees employees = employeesRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("해당 사원이 존재하지 않습니다."));
        return EmployeeDto.Summary.builder()
                .employeeId(employees.getEmployeeId())
                .firstName(employees.getFirstName())
                .lastName(employees.getLastName())
                .email(employees.getEmail())
                .phoneNumber(employees.getPhoneNumber())
                .hireDate(employees.getHireDate())
                .jobId(employees.getEmployeeId())
                .salary(employees.getSalary())
                .commissionPct(employees.getCommissionPct())
                .managerId(employees.getManagerId().getEmployeeId())
                .departmentId(employees.getEmployeeId())
                .build();
    }
}
