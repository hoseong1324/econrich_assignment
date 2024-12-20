package com.portfolio.server.departments.controller;

import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
import com.portfolio.server.departments.service.GetDepartmentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Tag(name = "005.부서", description = "부서 API")
public class DepartmentsController {

    private final ResponseService responseService;
    private final GetDepartmentsService getDepartmentsService;

    @GetMapping
    public CommonResult getDepartments(){
        return responseService.getListResult(getDepartmentsService.executeForList());
    }

    @GetMapping("/{departmentId}")
    public CommonResult getDepartment(@PathVariable int departmentId){
        return responseService.getSingleResult(getDepartmentsService.execute(departmentId));
    }
}
