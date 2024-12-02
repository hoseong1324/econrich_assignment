package com.portfolio.server.common.advice;

import com.portfolio.server.common.exception.CustomException;
import com.portfolio.server.common.exception.CustomUniversalException;
import com.portfolio.server.common.response.CommonResult;
import com.portfolio.server.common.response.ResponseService;
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
    public CommonResult customException(CustomException e, HttpServletRequest request) {
        return responseService.getCustomErrorResult(e, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomUniversalException.class)
    public CommonResult customUniversalException(CustomUniversalException e, HttpServletRequest request) {
        return responseService.getCustomUniversalResult(e, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public CommonResult severErrorException(RuntimeException e, HttpServletRequest request) {
        return responseService.getServerErrorResult(e, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public CommonResult methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        return responseService.getArgumentTypeMismatchResult(e, request);
    }

}
