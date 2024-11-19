package com.econrich.assignment.departments.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.departments.service.GetDepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentsController {

    private final ResponseService responseService;
    private final GetDepartmentsService getDepartmentsService;

    @GetMapping
    public CommonResult getDepartments(){
        return responseService.getListResult(getDepartmentsService.executeList());
    }

    @GetMapping("/{departmentId}")
    public CommonResult getDepartment(@PathVariable int departmentId){
        return responseService.getSingleResult(getDepartmentsService.execute(departmentId));
    }
}
