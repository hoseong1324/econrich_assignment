package com.econrich.assignment.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(HttpStatus.OK.value(), "success"),
    FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "fail");

    private final int code;
    private final String status;
}
