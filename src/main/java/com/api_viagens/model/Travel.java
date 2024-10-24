package com.api_viagens.model;

import jakarta.persistence.*;

@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Location source;

    @ManyToOne
    private Location destination;

    @Enumerated(EnumType.STRING)
    private TravelType type;

    @ManyToOne
    private Customer customer;

    public enum TravelType {
        ONE_WAY, RETURN, MULTI
    }

    public Object getStartDateTime() {
        throw new UnsupportedOperationException("Unimplemented method 'getStartDateTime'");
    }

    public void setId(Long id2) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

   
}
