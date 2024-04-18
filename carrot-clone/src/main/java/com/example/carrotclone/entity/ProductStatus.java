package com.example.carrotclone.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductStatus {
    ONSALE("ONSALE"), SOLD("SOLD"), RESERVED("RESERVED");
    private final String value;
}
