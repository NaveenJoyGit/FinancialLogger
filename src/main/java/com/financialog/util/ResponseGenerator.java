package com.financialog.util;

import com.financialog.dto.CommonResponse;

import java.net.http.HttpResponse;

public class ResponseGenerator {

    public static <T> CommonResponse<T> getSuccessResponse(T t, String message) {
        CommonResponse<T> successResponse = new CommonResponse<>(
                CommonResponse.ResponseCodeEnum.SUCCESS.getCode(),
                message,
                t
        );
        return successResponse;
    }

    public static <T> CommonResponse<T> getFailureResponse(T t, String message) {
        CommonResponse<T> failureResponse = new CommonResponse<>(
                CommonResponse.ResponseCodeEnum.FAILURE.getCode(),
                message,
                t
        );
        return failureResponse;
    }

}
