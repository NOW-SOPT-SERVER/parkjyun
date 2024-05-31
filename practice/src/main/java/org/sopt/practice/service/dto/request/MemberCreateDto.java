package org.sopt.practice.service.dto.request;

import org.sopt.practice.entity.Part;

public record MemberCreateDto(String name, Part part, int age) {
}
