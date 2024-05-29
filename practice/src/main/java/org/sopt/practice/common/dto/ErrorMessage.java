package org.sopt.practice.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
    /**
     * 401 UNAUTHORIZED
     * */
    JWT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패하였습니다."),
    /**
     * 403 FORBIDDEN
     * */
    MEMBER_FORBIDDEN(HttpStatus.FORBIDDEN.value(), "회원의 권한이 없습니다."),
    /**
     * 404 BAD_REQUEST
     * */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 회원을 찾을 수 없습니다"),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 블로그를 찾을 수 없습니다"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 포스트를 찾을 수 없습니다");

    private final int status;
    private final String message;
}
