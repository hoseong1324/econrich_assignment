package com.econrich.assignment.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST, "지원하지 않는 HTTP METHOD 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "유효하지 않은 인자입니다."),
    EMPLOYEES_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 직원입니다."),
    JOBS_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 직업입니다."),
    DEPARTMENTS_NOT_FOUNT(HttpStatus.BAD_REQUEST, "존재하지 않는 부서입니다."),
    LOCATIONS_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 지역입니다."),
    COUNTRIES_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 국가입니다.");

    private final HttpStatus status;
    private final String message;
}
