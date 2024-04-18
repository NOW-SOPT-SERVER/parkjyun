package com.example.carrotclone.dto.response;

public record MemberCreateResponse (long id) {
    public static MemberCreateResponse of(long id) {
        return new MemberCreateResponse(id);
    }
}
