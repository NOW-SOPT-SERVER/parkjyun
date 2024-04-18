package com.example.carrotclone.common.exception;

import com.example.carrotclone.common.code.failure.FailureCode;

public class MemberException extends CarrotException {
    public MemberException(FailureCode failureCode) {
        super(failureCode);
    }
}
