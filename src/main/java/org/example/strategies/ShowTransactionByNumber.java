package org.example.strategies;

import org.example.services.TransactionService;

public class ShowTransactionByNumber implements MenuStrategy{
    private final TransactionService transactionService = new TransactionService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        transactionService.fetchTransactionByNumber().forEach(System.out::println);
    }
}