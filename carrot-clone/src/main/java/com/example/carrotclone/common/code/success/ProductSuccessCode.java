package com.example.carrotclone.common.code.success;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProductSuccessCode implements SuccessCode {

    /**
     * 201 Created
     */
     PRODUCT_CREATE_SUCCESS(HttpStatus.CREATED, "상품 등록 성공");


    private final HttpStatus httpStatus;
    private final String message;
}
