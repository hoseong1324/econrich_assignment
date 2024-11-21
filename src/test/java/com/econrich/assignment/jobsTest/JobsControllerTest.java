package com.econrich.assignment.jobsTest;

import com.econrich.assignment.config.AbstractRestDocsTests;
import com.econrich.assignment.jobs.dto.JobHistoryDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class JobsControllerTest extends AbstractRestDocsTests {


    @Transactional
    @Test
    void getJobsHistory() throws Exception {

        mockMvc.perform(RestDocumentationRequestBuilders.get("/jobs/history/{employeeId}", 101)
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
                                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("데이터(리스트)"),
                                        fieldWithPath("data[].employeeId").type(NUMBER).description("직원 ID"),
                                        fieldWithPath("data[].startDate").type(JsonFieldType.STRING).description("근무 시작 일"),
                                        fieldWithPath("data[].endDate").type(JsonFieldType.STRING).description("근무 종료 일"),
                                        fieldWithPath("data[].jobId").type(JsonFieldType.STRING).description("직업 ID"),
                                        fieldWithPath("data[].departmentId").type(NUMBER).description("부서 ID")
                                )
                        )
                );
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @Test
    void updateJobs() throws Exception {
        JobHistoryDto.UpdateJobs updateJobs = new JobHistoryDto.UpdateJobs();
        updateJobs.setJobTitle("testJobTitle");
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/jobs/{jobId}", "AC_ACCOUNT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateJobs))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("jobId").description("직업 ID")
                                ),
                                requestFields(
                                        fieldWithPath("jobTitle").type(STRING).description("직업 명").optional(),
                                        fieldWithPath("minSalary").type(STRING).description("최소 급여").optional(),
                                        fieldWithPath("maxSalary").type(STRING).description("최대 급여").optional()
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

    @Transactional
    @Test
    void updateSalaryByPercent() throws Exception {
        JobHistoryDto.UpdateSalaryByPercent updateJobPercent = new JobHistoryDto.UpdateSalaryByPercent();
        mockMvc.perform(RestDocumentationRequestBuilders.patch("/jobs/{jobId}/salary", "AC_ACCOUNT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateJobPercent))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("jobId").description("직업 ID")
                                ),
                                requestFields(
                                        fieldWithPath("minSalaryPercent").type(STRING).description("최소 급여 퍼센트").optional(),
                                        fieldWithPath("maxSalaryPercent").type(STRING).description("최대 급여 퍼센트").optional()
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