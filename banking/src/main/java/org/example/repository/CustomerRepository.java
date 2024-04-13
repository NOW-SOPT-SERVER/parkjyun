package org.example.repository;

import org.example.common.FailureMessage;
import org.example.model.Account;
import org.example.model.BankType;
import org.example.model.Customer;
import org.example.model.CustomerGrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private static final String PARK = "박재연";
    private static final String CHOI = "최윤한";
    private static final int ACCOUNTNUMBER1 = 123456789;
    private static final int ACCOUNTNUMBER2 = 123456789;
    private static final int BALANCE = 1000000;

    private static Map<String, Customer> store = new HashMap<>();
    private static final CustomerRepository instance = new CustomerRepository();

    public static CustomerRepository getInstance() {
        return instance;
    }

    private CustomerRepository() {
        store.put(PARK, new Customer(PARK
                , List.of(new Account(ACCOUNTNUMBER1, BankType.NH, CustomerGrade.BASIC, BALANCE))));
        store.put(CHOI, new Customer(CHOI
                , List.of(new Account(ACCOUNTNUMBER2, BankType.KB, CustomerGrade.VIP, BALANCE*2),
                new Account(ACCOUNTNUMBER2, BankType.SHINHAN, CustomerGrade.VIP, BALANCE*3))));
    }

    public Customer findByName(String name) {
        Customer customer = store.get(name);
        if(customer == null) throw new IllegalArgumentException(FailureMessage.CUSTOMER_NOT_FOUND.getMessage());
        return customer;
    }
}
