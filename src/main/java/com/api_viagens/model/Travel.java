package com.api_viagens.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private BigDecimal amount;

    @ManyToOne
    private Location source;

    @ManyToOne
    private Location destination;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private TravelType type;

    @ManyToOne
    private Customer customer;

    public enum TravelType {
        ONE_WAY, RETURN, MULTI
    }

    // Getters e setters...
}
