package org.example.enums;

import lombok.Getter;

@Getter
public enum CardStatus {
    ACTIVE(1, "Activate"),
    BLOCKED(2, "Block");

    private final int value;
    private final String description;

    CardStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
