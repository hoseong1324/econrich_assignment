package com.portfolio.server.dataApisTest;

import com.portfolio.server.config.AbstractRestDocsTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DataApisControllerTest extends AbstractRestDocsTests {

    @Test
    @Transactional
    void getWeather() throws Exception {

        mockMvc.perform(get("/dataApis/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("si","서울")
                        .param("gu","동작구")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                                queryParameters(
                                        parameterWithName("si").description("시").optional(),
                                        parameterWithName("gu").description("구").optional(),
                                        parameterWithName("dong").description("동(로)").optional()
                                ),
                                responseFields(
                                        fieldWithPath("status").type(STRING).description("상태"),
                                        fieldWithPath("code").type(NUMBER).description("상태 코드"),
                                        fieldWithPath("message").type(STRING).description("에러 시 메시지").optional(),
                                        fieldWithPath("timestamp").type(STRING).description("통신 시간"),
                                        fieldWithPath("data").type(OBJECT).description("데이터"),
                                        fieldWithPath("data.si").type(STRING).description("시"),
                                        fieldWithPath("data.gu").type(STRING).description("구").optional(),
                                        fieldWithPath("data.dong").type(STRING).description("동(로)").optional(),
                                        fieldWithPath("data.weatherSummary[]").type(ARRAY).description("날씨정보"),
                                        fieldWithPath("data.weatherSummary[].category").type(STRING).description("카테고리"),
                                        fieldWithPath("data.weatherSummary[].obsrValue").type(STRING).description("값")
                                )
                        )
                )
        ;
    }
}