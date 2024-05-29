package org.sopt.practice.service.dto.response;

public record MemberJoinResponse(
        String accessToken,
        String memberId
) {

    public static MemberJoinResponse of(
            String accessToken,
            String userId
    ) {
        return new MemberJoinResponse(accessToken, userId);
    }
}
