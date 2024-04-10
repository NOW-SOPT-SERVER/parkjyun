package org.example.model;

public enum BankType {
    SHINHAN("신한"), KB("국민"), NH("농협");

    private final String bankName;

    BankType(String name) {
        this.bankName = this.name();
    }
}
