package org.example.strategies;

import org.example.services.CardService;

public class SearchCard implements MenuStrategy{
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        System.out.println("--------------------------------------------");
        System.out.println(cardService.searchCard());
    }
}
