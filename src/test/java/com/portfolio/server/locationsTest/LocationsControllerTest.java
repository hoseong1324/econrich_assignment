package com.portfolio.server.locationsTest;

import com.portfolio.server.config.AbstractRestDocsTests;
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

class LocationsControllerTest extends AbstractRestDocsTests {

    @Transactional
    @Test
    void getLocations() throws Exception {
        mockMvc.perform(get("/locations")
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
                                        fieldWithPath("data[].locationId").type(NUMBER).description("위치 ID"),
                                        fieldWithPath("data[].streetAddress").type(STRING).description(""),
                                        fieldWithPath("data[].postalCode").type(STRING).description("우편 번호").optional(),
                                        fieldWithPath("data[].city").type(STRING).description("도시"),
                                        fieldWithPath("data[].stateProvince").type(STRING).description("주 지방").optional(),
                                        fieldWithPath("data[].countryId").type(STRING).description("국가 ID")
                                )
                        )
                );
    }

    @Transactional
    @Test
    void getLocation() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/locations/{locationId}", 1000)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                pathParameters(
                                        parameterWithName("locationId").description("위치 ID")
                                ),
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("데이터(리스트)"),
                                        fieldWithPath("data.locationId").type(NUMBER).description("위치 ID"),
                                        fieldWithPath("data.streetAddress").type(STRING).description(""),
                                        fieldWithPath("data.postalCode").type(STRING).description("우편 번호").optional(),
                                        fieldWithPath("data.city").type(STRING).description("도시"),
                                        fieldWithPath("data.stateProvince").type(STRING).description("주 지방").optional(),
                                        fieldWithPath("data.countryId").type(STRING).description("국가 ID")
                                )
                        )
                );
    }
}