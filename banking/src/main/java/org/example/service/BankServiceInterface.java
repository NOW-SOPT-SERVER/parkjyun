package org.example.service;

import org.example.model.Account;

public interface BankServiceInterface {
    public int deposit(Account account, int amount);
    public int withdraw(Account account, int amount) throws IllegalArgumentException;
    public int getBalance(Account account);
    public int transfer(int amount,Account from, Account to);
    public Account findByNameAndAccountNumber(String name, int accountNumber);






}
