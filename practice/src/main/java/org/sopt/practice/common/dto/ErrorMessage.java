package org.sopt.practice.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
    /**
     * 400 BAD_REQUEST
     * */
    BAD_HEADER_STRUCTURE(HttpStatus.BAD_REQUEST.value(), "헤더의 구조가 잘못되었습니다."),
    /**
     * 401 UNAUTHORIZED
     * */
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 서명입니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 토큰입니다."),
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED.value(), "만료된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED.value(), "지원하지 않는 형식의 토큰입니다."),
    EMPTY_JWT(HttpStatus.UNAUTHORIZED.value(), "빈 토큰입니다."),
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
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 포스트를 찾을 수 없습니다"),
    REFRESHTOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 리프레시 토큰을 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
