package com.example.carrotclone.common.exception;

import com.example.carrotclone.common.code.failure.FailureCode;
import lombok.Getter;

@Getter
public class CarrotException extends RuntimeException {

    private final FailureCode failureCode;

    public CarrotException(FailureCode failureCode) {
        super(failureCode.getMessage());
        this.failureCode = failureCode;
    }

    public int getHttpStatusCode() {
        return failureCode.getHttpStatus().value();
    }
}
