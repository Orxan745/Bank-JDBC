package org.example.dao.entities;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String fatherName;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
}