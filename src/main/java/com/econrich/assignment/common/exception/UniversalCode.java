package com.econrich.assignment.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UniversalCode {

    private HttpStatus httpStatus;
    private String message;
}
