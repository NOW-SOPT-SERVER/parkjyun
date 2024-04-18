package com.example.carrotclone.common.code.failure;

import org.springframework.http.HttpStatus;

public interface FailureCode {
    HttpStatus getHttpStatus();
    String getMessage();

}
