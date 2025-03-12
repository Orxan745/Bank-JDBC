package org.example.strategies;

import org.example.dao.services.CardService;

public class CardToCard implements MenuStrategy{
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.moneyTransfer();
    }
}