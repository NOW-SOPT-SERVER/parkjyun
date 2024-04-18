package com.example.carrotclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum TradeType {
    SELL("SELL"), DONATE("DONATE");
    private final String value;
}
