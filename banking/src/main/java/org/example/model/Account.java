package org.example.model;

public class Account {
    private int accountNumber;
    private BankType bankType;
    private CustomerGrade grade;
    private int balance;

    public Account(int accountNumber, BankType bankType, CustomerGrade grade, int balance) {
        this.accountNumber = accountNumber;
        this.bankType = bankType;
        this.grade = grade;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int increaseBalance(int amount) {
        this.balance += amount;
        return balance;
    }

    public int decreaseBalance(int amount) {
        this.balance -= amount;
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

}
