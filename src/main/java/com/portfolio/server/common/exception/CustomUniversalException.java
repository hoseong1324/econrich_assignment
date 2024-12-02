package com.portfolio.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomUniversalException extends RuntimeException{

    private final UniversalCode universalCode;

    public CustomUniversalException(HttpStatus httpStatus, String message) {
        this.universalCode = new UniversalCode(httpStatus, message);
    }
}
