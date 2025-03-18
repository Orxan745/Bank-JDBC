package org.example.strategies;

import org.example.services.CustomerService;

public class UpdateCustomer implements MenuStrategy {
    private final CustomerService customerService = new CustomerService();
    @Override
    public void execute() {
        customerService.updateCustomer();
    }
}
