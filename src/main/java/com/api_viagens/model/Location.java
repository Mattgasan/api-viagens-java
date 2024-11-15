package com.api_viagens.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String name;

    @NotBlank(message = "O apelido é obrigatório!")
    private String nickname;

    @NotBlank(message = "O endereço é obrigatório!")
    private String address;

    @NotBlank(message = "A cidade é obrigatória!")
    private String city;

    @NotBlank(message = "O estado é obrigatório!")
    private String state;

    @NotBlank(message = "O país é obrigatório!")
    private String country;

    
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

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    
}
