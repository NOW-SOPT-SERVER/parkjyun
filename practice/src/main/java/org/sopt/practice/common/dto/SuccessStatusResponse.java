package org.sopt.practice.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record SuccessStatusResponse<T>(int status, String message, @JsonInclude(JsonInclude.Include.NON_NULL) T data) {
    public static SuccessStatusResponse<?> of(SuccessMessage successMessage) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), null);
    }

    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessStatusResponse(successMessage.getStatus(), successMessage.getMessage(), data);
    }
}
