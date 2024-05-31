package org.sopt.practice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Part {
    IOS("IOS"),
    ANDROID("ANDROID"),
    SERVER("SERVER"),
    WEB("WEB"),
    DESIGN("DESGIN"),
    PLAN("PLAN");

    private final String value;
}
