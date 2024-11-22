package com.econrich.assignment.common.response;

import com.econrich.assignment.common.exception.CustomException;
import com.econrich.assignment.common.exception.CustomUniversalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ResponseService {

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


    public CommonResult getServerErrorResult(RuntimeException exception) {
        CommonResult result = new CommonResult();
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        result.setCode(500);
        result.setMessage(exception.getMessage());
        return result;
    }

    public CommonResult getCustomErrorResult(CustomException exception) {
        CommonResult result = new CommonResult();
        result.setStatus(exception.getExceptionCode().name() + " : " +  exception.getExceptionCode().getStatus().getReasonPhrase());
        result.setCode(exception.getExceptionCode().getStatus().value());
        result.setMessage(exception.getExceptionCode().getMessage());
        return result;
    }

    public CommonResult getCustomUniversalResult(CustomUniversalException exception) {
        CommonResult result = new CommonResult();
        result.setStatus(exception.getUniversalCode().getHttpStatus().getReasonPhrase());
        result.setCode(exception.getUniversalCode().getHttpStatus().value());
        result.setMessage(exception.getUniversalCode().getMessage());
        return result;
    }

    // API 요청 성공 시 응답 모델을 성공 데이터로 세팅
    private void setSuccessResult(CommonResult result) {
        result.setStatus(CommonResponse.SUCCESS.getStatus());
        result.setCode(CommonResponse.SUCCESS.getCode());
    }

}
