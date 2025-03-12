package org.example.utils;

import org.example.enums.CardStatus;
import org.example.enums.Currency;
import org.example.enums.MenuElements;
import java.util.Arrays;

public class MenuUtil {

    public static void showMenuItems() {
        System.out.println("--------------------------------------------");
        Arrays.stream(MenuElements.values()).
                forEach(element ->
                        System.out.println(element.getValue() + "." + element.getDescription()));
        System.out.println("--------------------------------------------");
    }

    public static void showCurrencies() {
        Arrays.stream(Currency.values()).
                forEach(currency ->
                        System.out.println(currency.getValue() + ". " + currency.getDescription()));
    }

    public static void showCardStatuses() {
        System.out.println("--------------------------------------------");
        System.out.println("1.Activate" + "\n" +
                "2.Block");
    }

    public static MenuElements getElementByValue(int value) {
        try {
            return Arrays.stream(MenuElements.values()).
                    filter(element -> element.getValue() == value).
                    findFirst().
                    orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option: " + value);
            System.out.println("Try again!");
        }
        throw new RuntimeException();
    }

    public static Currency getCurrencyByValue(int value) {
        try {
            return Arrays.stream(Currency.values()).
                    filter(currency -> currency.getValue() == value).
                    findFirst().
                    orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: Wrong currency!");
        }
        throw new RuntimeException();
    }

    public static Currency getCurrencyByDescription(String description) {
        try {
            return Arrays.stream(Currency.values()).
                    filter(currency -> currency.getDescription().equals(description)).
                    findFirst().
                    orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: Wrong currency!");
        }
        throw new RuntimeException();
    }

    public static CardStatus getCardStatusByValue(int value) {
        try {
            return Arrays.stream(CardStatus.values()).
                    filter(cardStatus -> cardStatus.getValue() == value).
                    findFirst().
                    orElseThrow(IllegalArgumentException::new);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument: Wrong status!");
        }
        throw new RuntimeException();
    }
}