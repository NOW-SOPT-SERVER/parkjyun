package com.example.carrotclone.common.code.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    HttpStatus getHttpStatus();
    String getMessage();
}
