package com.econrich.assignment.countriesTest;

import com.econrich.assignment.config.AbstractRestDocsTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CountriesControllerTest extends AbstractRestDocsTests {
    @Transactional
    @Test
    void getCountries() throws Exception {
        mockMvc.perform(get("/countries")
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
                                        fieldWithPath("data[].countryId").type(STRING).description("국가 ID"),
                                        fieldWithPath("data[].countryName").type(STRING).description("국가 명"),
                                        fieldWithPath("data[].regionId").type(NUMBER).description("지역 ID")
                                )
                        )
                );
    }
}