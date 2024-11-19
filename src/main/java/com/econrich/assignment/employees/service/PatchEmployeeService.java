package com.econrich.assignment.employees.service;

import com.econrich.assignment.common.exception.CustomException;
import com.econrich.assignment.common.exception.ExceptionCode;
import com.econrich.assignment.departments.repo.DepartmentsRepository;
import com.econrich.assignment.employees.dto.EmployeesDto;
import com.econrich.assignment.employees.entity.Employees;
import com.econrich.assignment.employees.repo.EmployeesRepository;
import com.econrich.assignment.jobs.repo.JobsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class PatchEmployeeService {
    private final EmployeesRepository employeesRepository;
    private final JobsRepository jobsRepository;
    private final DepartmentsRepository departmentsRepository;

    @Transactional
    public void execute(int employeeId, EmployeesDto.UpdateForm form) {
        Employees employees = employeesRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new CustomException(ExceptionCode.EMPLOYEES_NOT_FOUND));

        updateEmployees(employees, form);
    }

    @Transactional
    protected void updateEmployees(Employees employees, EmployeesDto.UpdateForm form) {
        if (form.getFirstName() != null) employees.setFirstName(form.getFirstName());
        if (form.getLastName() != null) employees.setLastName(form.getLastName());
        if (form.getEmail() != null) employees.setEmail(form.getEmail());
        if (form.getHireDate() != null) employees.setHireDate(form.getHireDate());
        if (form.getJobId() != null) {
            employees.setJobs(jobsRepository.findById(form.getJobId())
                    .orElseThrow(() -> new CustomException(ExceptionCode.JOBS_NOT_FOUND)));
        }
        if (form.getSalary() != null || form.getCommissionPct() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            if (form.getSalary() != null) {
                employees.setSalary(new BigDecimal(decimalFormat.format(form.getSalary())));
            }
            if (form.getCommissionPct() != null) {
                employees.setCommissionPct(new BigDecimal(decimalFormat.format(form.getCommissionPct())));
            }
        }
        if (form.getManagerId() != null) {
            employees.setManager(employeesRepository.findById(form.getManagerId())
                    .orElseThrow(() -> new CustomException(ExceptionCode.EMPLOYEES_NOT_FOUND)));
        }
        if (form.getDepartmentId() != null) {
            employees.setDepartments(departmentsRepository.findById(form.getDepartmentId())
                    .orElseThrow(() -> new CustomException(ExceptionCode.DEPARTMENTS_NOT_FOUNT)));
        }
    }
}
