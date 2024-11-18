package com.econrich.assignment.common.response;

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

    // 실패 결과만 처리
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        setFailResult(result);
        return result;
    }

    // API 요청 성공 시 응답 모델을 성공 데이터로 세팅
    private void setSuccessResult(CommonResult result) {
        result.setStatus(CommonResponse.SUCCESS.getStatus());
        result.setCode(CommonResponse.SUCCESS.getCode());
    }

    // API 요청 실패 시 응답 모델을 실패 데이터로 세팅
    private void setFailResult(CommonResult result) {
        result.setStatus(CommonResponse.FAIL.getStatus());
        result.setCode(CommonResponse.FAIL.getCode());
    }
}
