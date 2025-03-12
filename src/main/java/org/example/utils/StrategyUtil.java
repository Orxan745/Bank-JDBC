package org.example.utils;

import org.example.dao.entities.Card;
import java.util.Random;

public class StrategyUtil {
    public static String getRandomNumber(int numberLength) {
        Random random = new Random();
        int a;
        char b;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < numberLength; i++) {
            a = random.nextInt(48, 58);
            b = (char) a;
            number.append(b);
        }
        return number.toString();
    }

    public static Double currencyTransformation(Card sender, Card receiver, Double amount) {
        double transformedAmount = amount * sender.getCurrency().getDollarAmount();
        transformedAmount = transformedAmount / receiver.getCurrency().getDollarAmount();
        return transformedAmount;
    }
}