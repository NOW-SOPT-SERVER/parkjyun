package org.sopt.practice.exception;

import org.sopt.practice.common.dto.ErrorMessage;

public class BadRequestException extends BusinessException {
    public BadRequestException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
