package com.econrich.assignment.employees.controller;

import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import com.econrich.assignment.employees.dto.EmployeesDto;
import com.econrich.assignment.employees.service.GetEmployeeService;
import com.econrich.assignment.employees.service.PatchEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name = "006.사원", description = "사원 API")
public class EmployeesController {

    private final ResponseService responseService;
    private final GetEmployeeService getEmployeeService;
    private final PatchEmployeeService patchEmployeeService;

    /**
     *  사원 전체 조회
     */
    @GetMapping
    public CommonResult getEmployees() {
        return responseService.getListResult(getEmployeeService.executeForList());
    }

    /**
     * 특정 사원 조회
     */
    @GetMapping("/{employeeId}")
    public CommonResult getEmployee(@PathVariable int employeeId) {
        return responseService.getSingleResult(getEmployeeService.execute(employeeId));
    }

    /**
     * 특정 사원 수정
     * description :  성, 이름, 이메일 사이즈 Valid 나머지 데이터는 Null 체크 후 업데이트
     */
    @PatchMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult patchEmployee(@PathVariable int employeeId, @Valid @RequestBody EmployeesDto.UpdateForm form) {
        patchEmployeeService.execute(employeeId, form);
        return responseService.getSuccessResult();
    }
}
