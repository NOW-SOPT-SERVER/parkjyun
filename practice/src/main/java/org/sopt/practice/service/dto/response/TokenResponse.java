package org.sopt.practice.service.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        Long memberId
) {

    public static TokenResponse of(
            String accessToken,
            String refreshToken,
            Long userId
    ) {
        return new TokenResponse(accessToken, refreshToken, userId);
    }
}
