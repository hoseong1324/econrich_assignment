package com.portfolio.server.common.response;

import com.portfolio.server.common.exception.CustomException;
import com.portfolio.server.common.exception.CustomUniversalException;
import com.portfolio.server.common.service.SlackSendService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {

    private final SlackSendService slackSendService;

    // 단일 결과 리턴
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 복수 결과 리턴
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    // 현재 Exception 관련 전체 슬랙 발송중 - 통신 확인용
    // TODO Client Error 제외 및 필요 사항만 발송으로 수정

    public CommonResult getServerErrorResult(RuntimeException exception, HttpServletRequest request) {
        log.error(exception.getMessage());
        CommonResult result = new CommonResult();
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        result.setCode(500);
        result.setMessage(exception.getMessage());
        slackSendService.sendSlackMessage(request, result);
        return result;
    }

    public CommonResult getCustomErrorResult(CustomException exception, HttpServletRequest request) {
        CommonResult result = new CommonResult();
        result.setStatus(exception.getExceptionCode().name() + " : " +  exception.getExceptionCode().getStatus().getReasonPhrase());
        result.setCode(exception.getExceptionCode().getStatus().value());
        result.setMessage(exception.getExceptionCode().getMessage());
        slackSendService.sendSlackMessage(request, result);
        return result;
    }

    public CommonResult getArgumentTypeMismatchResult(MethodArgumentTypeMismatchException exception, HttpServletRequest request){
        log.warn(exception.getMessage());
        CommonResult result = new CommonResult();
        result.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.setCode(400);
        result.setMessage(exception.getMessage());
        slackSendService.sendSlackMessage(request, result);
        return result;
    }

    public CommonResult getCustomUniversalResult(CustomUniversalException exception, HttpServletRequest request) {
        CommonResult result = new CommonResult();
        result.setStatus(exception.getUniversalCode().getHttpStatus().getReasonPhrase());
        result.setCode(exception.getUniversalCode().getHttpStatus().value());
        result.setMessage(exception.getUniversalCode().getMessage());
        slackSendService.sendSlackMessage(request, result);
        return result;
    }

    // API 요청 성공 시 응답 모델을 성공 데이터로 세팅
    private void setSuccessResult(CommonResult result) {
        result.setStatus(CommonResponse.SUCCESS.getStatus());
        result.setCode(CommonResponse.SUCCESS.getCode());
    }

}
