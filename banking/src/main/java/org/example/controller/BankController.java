package org.example.controller;

import org.example.model.Account;
import org.example.service.BankServiceImpl;
import org.example.service.BankServiceInterface;
import org.example.view.InputView;
import org.example.view.OutPutView;

public class BankController {
    private static final byte DEPOSIT = 1;
    private static final byte WITHDRAW = 2;
    private static final byte GET_BALANCE = 3;
    private static final byte TRANSFER = 4;
    private static final byte STOP = 5;

    private BankServiceInterface bankServiceImpl;
    private OutPutView outputView;
    private InputView inputView;
    private static final BankController bankController = new BankController();

    private BankController () {
        this.bankServiceImpl = BankServiceImpl.getInstance();
        this.outputView = OutPutView.getInstance();
        this.inputView = InputView.getInstance();
    }

    public static BankController getInstance() {
        return bankController;
    }

    public void startService() {
        boolean isContinue = true;
        while(isContinue) {
            switch (inputView.getMenuNumber()) {
                case DEPOSIT :
                    deposit();
                    break;
                case WITHDRAW :
                    withdraw();
                    break;
                case GET_BALANCE :
                    getBalance();
                    break;
                case TRANSFER :
                    transfer();
                    break;
                case STOP :
                    stopService();
                    isContinue = false;
            }
        }
    }

    private void stopService() {
        outputView.stopService();
    }

    private void deposit() {
        try {
            String name = inputView.getName();
            int accountNumber = inputView.getAccountNumber();
            Account account = bankServiceImpl.findByNameAndAccountNumber(name, accountNumber);
            int depositAmount = inputView.getDepositAmount();
            outputView.printDepositMessage(depositAmount, bankServiceImpl.deposit(account, depositAmount));
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void withdraw() {
        try {
            String name = inputView.getName();
            int accountNumber = inputView.getAccountNumber();
            Account account = bankServiceImpl.findByNameAndAccountNumber(name, accountNumber);
            int withDrawAmount = inputView.getWithDrawAmount();
            outputView.printWithDrawMessage(withDrawAmount, bankServiceImpl.withdraw(account, withDrawAmount));
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void getBalance() {
        try {
            String name = inputView.getName();
            int accountNumber = inputView.getAccountNumber();
            int balance = bankServiceImpl.getBalance(bankServiceImpl.findByNameAndAccountNumber(name, accountNumber));
            outputView.printBalanceMessage(balance);
        } catch (Exception e) {
            handleException(e);
        }

    }
    private void transfer() {
        try {
            Account senderAccount = bankServiceImpl.findByNameAndAccountNumber(inputView.getSenderName(), inputView.getAccountNumber());
            Account receiverName = bankServiceImpl.findByNameAndAccountNumber(inputView.getReceiverName(), inputView.getAccountNumber());
            int transferAmount = inputView.getTransferAmount();
            int senderBalance = bankServiceImpl.transfer(transferAmount, senderAccount, receiverName);
            outputView.printTransferMessage(transferAmount, senderBalance);
        } catch (Exception e) {
            handleException(e);
        }

    }

    private void handleException(Exception e) {
        outputView.printErrorMessage(e);
    }
}
