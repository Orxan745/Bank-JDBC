package org.example.strategies;

import org.example.dao.services.TransactionService;

public class ShowAllCustomerTransactions implements MenuStrategy{
    private final TransactionService transactionService = new TransactionService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        transactionService.fetchCustomerTransactions().forEach(System.out::println);
    }
}