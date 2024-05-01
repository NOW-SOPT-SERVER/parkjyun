package org.sopt.practice.exception;

import org.sopt.practice.common.dto.ErrorMessage;

public class ForbiddenException extends BusinessException {
    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
