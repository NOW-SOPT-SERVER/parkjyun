package org.sopt.practice.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum SuccessMessage {
    /**
     * 200 OK
     * */
    BLOG_GET_SUCCESS(HttpStatus.OK.value(), "블로그 조회에 성공했습니다"),
    POST_GET_SUCCESS(HttpStatus.OK.value(), "포스트 조회에 성공했습니다"),
    /**
     * 201 CREATED
     * */
    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다");

    private final int status;
    private final String message;
}
