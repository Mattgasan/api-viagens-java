package com.api_viagens.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private String address;
    private String city;
    private BigDecimal state;
    private String country;
	public Object getName() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

    // Getters e setters...
}
