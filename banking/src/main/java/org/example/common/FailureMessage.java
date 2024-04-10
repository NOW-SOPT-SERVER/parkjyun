package org.example.common;

public enum FailureMessage {
    NOT_ENOUGH_BALANCE("계좌 잔액이 부족합니다"),
    CUSTOMER_NOT_FOUND("이름을 잘못 입력하셨습니다"),
    ACCOUNT_NOT_FOUND("해당하는 계좌가 없습니다");

    private final String message;

    FailureMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
