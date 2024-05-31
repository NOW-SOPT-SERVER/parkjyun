package org.sopt.practice.service.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenType {
    ACCESS("ACCESS"), REFRESH("REFRESH");

    private final String value;
}
