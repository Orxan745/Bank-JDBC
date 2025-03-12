package org.example.strategies;

import org.example.dao.services.CustomerService;

public class RemoveCustomer implements MenuStrategy {
    private final CustomerService customerService = new CustomerService();
    @Override
    public void execute() {
        customerService.removeCustomer();
    }
}