package org.sopt.practice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponse {

    String content;

    public static ApiResponse create(String content) {
        return new ApiResponse(content);
    }
}
