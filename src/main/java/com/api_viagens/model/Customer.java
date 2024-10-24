package com.api_viagens.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String address;
    private String city;
    private BigDecimal state;
    private String country;
    private LocalDate birthDate;
    private BigDecimal limitAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }

    // Getters e setters...
}
