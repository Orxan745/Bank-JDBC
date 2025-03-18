package org.example.services;

import org.example.dao.entities.Transaction;
import org.example.dao.repositories.TransactionRepository;
import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private final TransactionRepository transactionRepository = new TransactionRepository();

    public List<Transaction> fetchTransactionByNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the transaction ID: ");
        Long customerNumber = scanner.nextLong();
        return transactionRepository.fetchTransactionByNumber(customerNumber);
    }

    public List<Transaction> fetchCustomerTransactions() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the customer ID: ");
        Long customerID = scanner.nextLong();
        return transactionRepository.fetchCustomerTransactions(customerID);
    }
}