package com.example.carrotclone.common.dto;

import com.example.carrotclone.common.code.failure.FailureCode;
import com.example.carrotclone.common.code.success.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarrotResponse<T> {
    private final int status;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static CarrotResponse<?> success(SuccessCode successCode) {
        return new CarrotResponse<>(successCode.getHttpStatus().value(), successCode.getMessage());
    }

    public static <T>CarrotResponse<T> success(SuccessCode successCode, T data) {
        return new CarrotResponse<>(successCode.getHttpStatus().value(), successCode.getMessage(), data);
    }

    public static CarrotResponse<?> error(FailureCode failureCode) {
        return new CarrotResponse<>(failureCode.getHttpStatus().value(), failureCode.getMessage());
    }

    public static CarrotResponse<?> error(HttpStatusCode httpStatusCode, String message) {
        return new CarrotResponse<>(httpStatusCode.value(), message);
    }



}