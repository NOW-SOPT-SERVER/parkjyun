package org.example.service;

import org.example.common.FailureMessage;
import org.example.model.Account;
import org.example.repository.CustomerRepository;

import java.util.List;

public class BankServiceImpl implements BankServiceInterface {
    private final CustomerRepository customerRepository;
    private static final BankServiceImpl instance = new BankServiceImpl();

    private BankServiceImpl() {
        customerRepository = CustomerRepository.getInstance();
    }

    public static BankServiceImpl getInstance() {
        return instance;
    }

    @Override
    public int deposit(Account account, int amount) {
        return account.increaseBalance(amount);
    }

    @Override
    public int withdraw(Account account, int amount) throws IllegalArgumentException {
        if(account.getBalance() < amount) throw new IllegalArgumentException(FailureMessage.NOT_ENOUGH_BALANCE.getMessage());
        return account.decreaseBalance(amount);
    }

    @Override
    public int getBalance(Account account) {
        return account.getBalance();
    }

    @Override
    public int transfer(int amount, Account from, Account to) throws IllegalArgumentException {
        if(from.getBalance() < amount) throw new IllegalArgumentException(FailureMessage.NOT_ENOUGH_BALANCE.getMessage());
        int senderBalance = from.decreaseBalance(amount);
        to.increaseBalance(amount);
        return senderBalance;
    }

    @Override
    public Account findByNameAndAccountNumber(String name, int accountNumber) {
        List<Account> accounts = customerRepository.findByName(name).getAccounts();
        if (accounts.isEmpty()) throw new IllegalArgumentException(FailureMessage.CUSTOMER_NOT_FOUND.getMessage());
        return accounts.stream()
                .filter(account -> account.getAccountNumber() == accountNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(FailureMessage.ACCOUNT_NOT_FOUND.getMessage()));
    }
}
