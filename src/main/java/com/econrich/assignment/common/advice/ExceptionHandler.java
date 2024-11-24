package com.econrich.assignment.common.advice;

import com.econrich.assignment.common.exception.CustomException;
import com.econrich.assignment.common.exception.CustomUniversalException;
import com.econrich.assignment.common.response.CommonResult;
import com.econrich.assignment.common.response.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    private final ResponseService responseService;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    public CommonResult customException(CustomException e) {
        return responseService.getCustomErrorResult(e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomUniversalException.class)
    public CommonResult customUniversalException(CustomUniversalException e) {
        return responseService.getCustomUniversalResult(e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public CommonResult severErrorException(RuntimeException e) {
        return responseService.getServerErrorResult(e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public CommonResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        return responseService.getArgumentTypeMismatchResult(e);
    }

}
