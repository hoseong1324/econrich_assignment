package com.econrich.assignment.departmentsTest;

import com.econrich.assignment.config.AbstractRestDocsTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepartmentsControllerTest extends AbstractRestDocsTests {
    @Transactional
    @Test
    void getDepartments() throws Exception {
        mockMvc.perform(get("/departments")
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
                                        fieldWithPath("data[].departmentId").type(NUMBER).description("부서 ID"),
                                        fieldWithPath("data[].departmentName").type(STRING).description("부서 명"),
                                        fieldWithPath("data[].managerId").type(NUMBER).description("직원 ID").optional(),
                                        fieldWithPath("data[].locationId").type(NUMBER).description("위치 ID")
                                )
                        )
                );
    }
    @Transactional
    @Test
    void getDepartment() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/departments/{departmentId}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("departmentId").description("부서 ID")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("데이터"),
                                        fieldWithPath("data.departmentId").type(NUMBER).description("부서 ID"),
                                        fieldWithPath("data.departmentName").type(STRING).description("부서 명"),
                                        fieldWithPath("data.managerId").type(NUMBER).description("직원 ID"),
                                        fieldWithPath("data.locationId").type(NUMBER).description("위치 ID")
                                )
                        )
                );
    }
}