package org.example;

import org.example.controller.BankController;

public class Main {
    public static void main(String[] args) {
        BankController bankController = BankController.getInstance();
        bankController.startService();
    }
}