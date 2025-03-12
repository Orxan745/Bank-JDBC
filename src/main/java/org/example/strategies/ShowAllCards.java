package org.example.strategies;

import org.example.dao.services.CardService;

public class ShowAllCards implements MenuStrategy{
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        cardService.fetchCustomerCards().forEach(System.out::println);
    }
}