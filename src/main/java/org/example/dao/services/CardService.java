package org.example.dao.services;

import org.example.dao.entities.Card;
import org.example.dao.entities.Customer;
import org.example.dao.repositories.CardRepository;
import org.example.dao.repositories.CustomerRepository;
import org.example.enums.CardStatus;
import org.example.enums.Currency;
import static org.example.utils.StrategyUtil.getRandomNumber;
import static org.example.utils.MenuUtil.showCurrencies;
import static org.example.utils.MenuUtil.getCurrencyByValue;
import static org.example.utils.MenuUtil.showCardStatuses;
import static org.example.utils.MenuUtil.getCardStatusByValue;
import static org.example.utils.StrategyUtil.currencyTransformation;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CardService {
    private final CardRepository cardRepository = new CardRepository();

    public void saveCard() {
        cardRepository.saveCard(buildCard());
        System.out.println("Card saved successfully!");
    }

    public Card searchCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the card number: ");
        String cardNumber = scanner.next();
        return cardRepository.searchCard(cardNumber);
    }

    public void updateCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter card number: ");
        String cardNumber = scanner.next();
        Card card = cardRepository.searchCard(cardNumber);
        System.out.println("Card found, please enter the card info!");
        System.out.print("Customer ID: ");
        Long customerID = scanner.nextLong();
        System.out.println("Please select currency!");
        showCurrencies();
        System.out.print("Option: ");
        int option = scanner.nextInt();
        Currency currency = getCurrencyByValue(option);
        System.out.print("Please enter the amount: ");
        Double amount = scanner.nextDouble();
        card.setCustomerID(customerID);
        card.setCurrency(currency);
        card.setAmount(card.getAmount()+amount);
        cardRepository.updateCard(card);
        System.out.println("Card updated successfully!");
    }

    public void updateCardStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the card number: ");
        String cardNumber = scanner.next();
        Card card = cardRepository.searchCard(cardNumber);
        if (card.getIsActive()) {
            System.out.println("Card is active!");
        } else {
            System.out.println("Card is not active!");
        }
        showCardStatuses();
        System.out.print("Please select an option: ");
        int option = scanner.nextInt();
        CardStatus cardStatus = getCardStatusByValue(option);
        boolean status;
        status = cardStatus.getValue() == 1;
        cardRepository.updateCardStatus(cardNumber, status);
        if (status) {
            System.out.println("Card activated!");
        } else {
            System.out.println("Card blocked!");
        }
    }

    public void moneyTransfer() {
        CustomerRepository customerRepository = new CustomerRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the card number (Sender): ");
        String cardNumberFrom = scanner.next();
        Card cardFrom = cardRepository.searchCard(cardNumberFrom);
        System.out.print("Please enter the card number (Receiver): ");
        String cardNumberTo = scanner.next();
        Card cardTo = cardRepository.searchCard(cardNumberTo);
        System.out.print("Please enter the amount: ");
        Double amount = scanner.nextDouble();
        Double transformedAmount = currencyTransformation(cardFrom, cardTo, amount);
        Customer customerFrom = customerRepository.searchCustomer(cardFrom.getCustomerID());
        Customer customerTo = customerRepository.searchCustomer(cardTo.getCustomerID());
        cardRepository.moneyTransfer(cardFrom, cardTo, transformedAmount, customerFrom, customerTo);
        System.out.println("Transfer ended successfully");
    }

    public List<Card> fetchCustomerCards() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the customer ID: ");
        Long customerID = scanner.nextLong();
        return cardRepository.fetchCustomerCards(customerID);
    }

    private Card buildCard() {
        CustomerService customerService = new CustomerService();
        Scanner scanner = new Scanner(System.in);
        Customer customer = customerService.searchCustomer();
        System.out.println("Please select currency!");
        showCurrencies();
        System.out.print("Option: ");
        int option = scanner.nextInt();
        Currency currency = getCurrencyByValue(option);
        return Card.builder()
                .customerID(customer.getId())
                .cardNumber(getRandomNumber(16))
                .cvv(getRandomNumber(3))
                .currency(currency)
                .amount(0D)
                .createdAt(LocalDate.now())
                .isActive(true)
                .build();

    }
}