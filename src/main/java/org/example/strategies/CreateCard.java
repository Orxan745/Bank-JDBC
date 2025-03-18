package org.example.strategies;

import org.example.services.CardService;

public class CreateCard implements MenuStrategy {
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.saveCard();
    }
}