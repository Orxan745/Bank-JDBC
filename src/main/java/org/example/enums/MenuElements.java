package org.example.enums;

import lombok.Getter;
import org.example.strategies.*;

@Getter
public enum MenuElements {
    CREATE_CUSTOMER(1, "Create customer", new CreateCustomer()),
    UPDATE_CUSTOMER(2, "Update customer", new UpdateCustomer()),
    SEARCH_CUSTOMER(3, "Search customer", new SearchCustomer()),
    REMOVE_CUSTOMER(4, "Remove customer", new RemoveCustomer()),
    CREATE_CARD(5, "Create card", new CreateCard()),
    UPDATE_CARD(6, "Update card", new UpdateCard()),
    SEARCH_CARD(7, "Search card", new SearchCard()),
    UPDATE_CARD_STATUS(8, "Update card status", new UpdateCardStatus()),
    CARD_TO_CARD(9, "Money transfer", new CardToCard()),
    SHOW_ALL_CUSTOMERS(10, "Show all customers", new ShowAllCustomers()),
    SHOW_ALL_CUSTOMER_CARDS(11, "Show all customer cards", new ShowAllCards()),
    SHOW_TRANSACTION_BY_NUMBER(12, "Show transaction by number", new ShowTransactionByNumber()),
    SHOW_ALL_CUSTOMER_TRANSACTIONS(13, "Show all customer transactions", new ShowAllCustomerTransactions()),
    EXIT(14, "Exit", new Exit());

    private final int value;
    private final String description;
    private final MenuStrategy strategy;

    MenuElements(int value, String description, MenuStrategy strategy) {
        this.value = value;
        this.description = description;
        this.strategy = strategy;
    }
}