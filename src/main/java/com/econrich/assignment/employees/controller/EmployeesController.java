package com.econrich.assignment.employees.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.employees.service.GetEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final ResponseService responseService;
    private final GetEmployeeService getEmployeeService;


    @GetMapping
    public CommonResult getEmployees(){
        return responseService.getListResult(getEmployeeService.getEmplyeesList());
    }

    @GetMapping("/{employeeId}")
    public CommonResult getEmployee(@PathVariable Long employeeId){
        return responseService.getSingleResult(getEmployeeService.getEmployee(employeeId));
    }
}
