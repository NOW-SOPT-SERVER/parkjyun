package org.sopt.practice.service.dto;

import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(@Size(max = 10, message = "블로그 제목 10글자 이내") String title) {
}
