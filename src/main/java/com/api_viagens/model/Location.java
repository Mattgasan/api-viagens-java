package com.api_viagens.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres!")
    private String name;

    @NotBlank(message = "O apelido é obrigatório!")
    @Size(min = 3, max = 100, message = "O apelido deve ter entre 3 e 100 caracteres!")
    private String nickname;

    @NotBlank(message = "O endereço é obrigatório!")
    @Size(min = 10, max = 120, message = "O endereço deve ter entre 10 e 120 caracteres!")
    private String address;

    @NotBlank(message = "A cidade é obrigatória!")
    @Size(min = 5, max = 30, message = "O nome da cidade deve ter entre 5 e 30 caracteres!")
    private String city;

    @NotBlank(message = "O estado é obrigatório!")
    @Size(min = 5, max = 30, message = "O nome do estado deve ter entre 5 e 30 caracteres!")
    private String state;

    @NotBlank(message = "O país é obrigatório!")
    @Size(min = 3, max = 30, message = "O nome do país deve ter entre 3 e 30 caracteres!")
    private String country;

    public Location() {
    }

    public Location(String name, String nickname, String address, String city, String state, String country) {
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Location location = (Location) obj;
        return id != null && id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // ------------- GETTERS & SETTERS --------------
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
