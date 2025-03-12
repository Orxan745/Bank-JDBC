package org.example.dao.entities;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Transaction {
    private Long id;
    private Long customerIdFrom;
    private Long customerIdTo;
    private Long cardIdFrom;
    private Long cardIdTo;
    private Double amount;
    private LocalDate dateOfTransaction;
}
