package com.portfolio.server.employeesTest;

import com.portfolio.server.config.AbstractRestDocsTests;
import com.portfolio.server.employees.dto.EmployeesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeesControllerTest extends AbstractRestDocsTests {
    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @Test
    void getEmployees() throws Exception {
        mockMvc.perform(get("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간"),
                                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("데이터(리스트)"),
                                        fieldWithPath("data[].employeeId").type(NUMBER).description("직원 ID"),
                                        fieldWithPath("data[].firstName").type(STRING).description("성"),
                                        fieldWithPath("data[].lastName").type(STRING).description("이름"),
                                        fieldWithPath("data[].email").type(STRING).description("이메일"),
                                        fieldWithPath("data[].phoneNumber").type(STRING).description("연락처"),
                                        fieldWithPath("data[].hireDate").type(STRING).description("입사일"),
                                        fieldWithPath("data[].jobId").type(STRING).description("직업 ID"),
                                        fieldWithPath("data[].salary").type(NUMBER).description("급여"),
                                        fieldWithPath("data[].commissionPct").type(NUMBER).description("수수료").optional(),
                                        fieldWithPath("data[].managerId").type(NUMBER).description("관리자 ID").optional(),
                                        fieldWithPath("data[].departmentId").type(NUMBER).description("부서 ID").optional()
                                )
                        )
                );
    }

    @Transactional
    @Test
    void getEmployee() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/employees/{employeeId}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("employeeId").description("직원 ID")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("데이터"),
                                        fieldWithPath("data.employeeId").type(NUMBER).description("직원 ID"),
                                        fieldWithPath("data.firstName").type(STRING).description("성"),
                                        fieldWithPath("data.lastName").type(STRING).description("이름"),
                                        fieldWithPath("data.email").type(STRING).description("이메일"),
                                        fieldWithPath("data.phoneNumber").type(STRING).description("연락처"),
                                        fieldWithPath("data.hireDate").type(STRING).description("입사일"),
                                        fieldWithPath("data.jobId").type(STRING).description("직업 ID"),
                                        fieldWithPath("data.salary").type(NUMBER).description("급여"),
                                        fieldWithPath("data.commissionPct").type(NUMBER).description("수수료").optional(),
                                        fieldWithPath("data.managerId").type(NUMBER).description("관리자 ID").optional(),
                                        fieldWithPath("data.departmentId").type(NUMBER).description("부서 ID").optional()
                                )
                        )
                );
    }
    @Transactional
    @Test
    void patchEmployee() throws Exception {
        EmployeesDto.UpdateForm updateForm = new EmployeesDto.UpdateForm();
        updateForm.setFirstName("testFirstName");
        updateForm.setLastName("testLastName");
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/employees/{employeeId}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateForm))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("employeeId").description("직원 ID")
                                ),
                                requestFields(
                                        fieldWithPath("firstName").type(STRING).description("성").optional(),
                                        fieldWithPath("lastName").type(STRING).description("이름").optional(),
                                        fieldWithPath("email").type(STRING).description("이메일").optional(),
                                        fieldWithPath("phoneNumber").type(STRING).description("연락처").optional(),
                                        fieldWithPath("hireDate").type(STRING).description("입사일").optional(),
                                        fieldWithPath("jobId").type(STRING).description("직업 ID").optional(),
                                        fieldWithPath("salary").type(NUMBER).description("급여").optional(),
                                        fieldWithPath("commissionPct").type(NUMBER).description("수수료").optional(),
                                        fieldWithPath("managerId").type(NUMBER).description("관리자 ID").optional(),
                                        fieldWithPath("departmentId").type(NUMBER).description("부서 ID").optional()
                                ),
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간")
                                )
                        )
                );
    }
}