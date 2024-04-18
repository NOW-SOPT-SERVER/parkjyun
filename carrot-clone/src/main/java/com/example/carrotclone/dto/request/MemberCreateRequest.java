package com.example.carrotclone.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(@NotBlank String nickname) {
}
