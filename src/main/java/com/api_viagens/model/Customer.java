package com.api_viagens.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String name;

    @NotBlank(message = "O sobrenome é obrigatório!")
    private String lastname;

    @NotBlank(message = "O endereço é obrigatório!")
    private String address;

    @NotBlank(message = "A cidade é obrigatória!")
    private String city;

    @NotNull(message = "O estado é obrigatório!")
    private String state;

    @NotBlank(message = "O país é obrigatório!")
    private String country;

    @NotNull(message = "A data de nascimento é obrigatória!")
    private LocalDate birthDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "O limite deve ser maior que zero.")
    private BigDecimal limitAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE, INACTIVE
    }

    // ------------- GETTERS & SETTERS!! --------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}