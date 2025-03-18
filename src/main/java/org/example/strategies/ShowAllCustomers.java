package org.example.strategies;

import org.example.services.CustomerService;

public class ShowAllCustomers implements MenuStrategy {
    private final CustomerService customerService = new CustomerService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        customerService.fetchAllCustomers().forEach(System.out::println);
    }
}