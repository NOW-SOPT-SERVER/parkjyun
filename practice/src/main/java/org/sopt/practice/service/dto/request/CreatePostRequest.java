package org.sopt.practice.service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePostRequest(@NotBlank String name, @NotBlank String content) {
}
