package org.sopt.practice.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
    /**
     * 403 FORBIDDEN
     * */
    MEMBER_FORBIDDEN(403, "회원의 권한이 없습니다."),
    /**
     * 404 BAD_REQUEST
     * */
    MEMBER_NOT_FOUND(404, "해당 회원을 찾을 수 없습니다"),
    BLOG_NOT_FOUND(404, "해당하는 블로그를 찾을 수 없습니다"),
    POST_NOT_FOUND(404, "해당하는 포스트를 찾을 수 없습니다");

    private final int status;
    private final String message;
}
