package org.example.dao.entities;

import lombok.*;
import org.example.enums.Currency;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Card {
    private Long id;
    private Long customerID;
    private String cardNumber;
    private String cvv;
    private Currency currency;
    private Double amount;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
}