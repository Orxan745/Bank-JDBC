package org.example.strategies;

import org.example.services.CustomerService;

public class SearchCustomer implements MenuStrategy{
    private final CustomerService customerService = new CustomerService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        System.out.println(customerService.searchCustomer());
    }
}
