package org.example.enums;

import lombok.Getter;

@Getter
public enum Currency {
    AZN(1, "AZN", 0.58),
    USD(2, "USD", 1.0),
    RUB(3, "RUB", 0.009),
    TRY(4, "TRY", 0.028);

    private final int value;
    private final String description;
    private final Double dollarAmount;

    Currency(int value, String description, Double dollarAmount) {
        this.value = value;
        this.description = description;
        this.dollarAmount = dollarAmount;
    }
}