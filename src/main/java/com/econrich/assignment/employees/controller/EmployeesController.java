package com.econrich.assignment.employees.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.employees.dto.EmployeesDto;
import com.econrich.assignment.employees.service.GetEmployeeService;
import com.econrich.assignment.employees.service.PatchEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final ResponseService responseService;
    private final GetEmployeeService getEmployeeService;
    private final PatchEmployeeService patchEmployeeService;


    @GetMapping
    public CommonResult getEmployees() {
        return responseService.getListResult(getEmployeeService.executeForList());
    }

    @GetMapping("/{employeeId}")
    public CommonResult getEmployee(@PathVariable int employeeId) {
        return responseService.getSingleResult(getEmployeeService.execute(employeeId));
    }

    @PatchMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult patchEmployee(@PathVariable int employeeId, @Valid @RequestBody EmployeesDto.UpdateForm form) {
        patchEmployeeService.execute(employeeId, form);
        return responseService.getSuccessResult();
    }
}
