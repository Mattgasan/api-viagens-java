package com.api_viagens.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O número do pedido é obrigatório!!")
    private String orderNumber;

    @Column(nullable = false, precision = 10, scale = 2)
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero!!")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "source_id", nullable = false)
    private Location source;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Location destination;

    @NotNull(message = "A data de início é obrigatória!!")
    private LocalDateTime startDateTime;

    @NotNull(message = "A data de término é obrigatória!!")
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo de viagem é obrigatório!!")
    private TravelType type;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da viagem é obrigatório!!")
    private TravelStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "O cliente é obrigatório!!")
    private Customer customer;

    public Travel() {
    }

    public Travel(String orderNumber, BigDecimal amount, Location source, Location destination,
            LocalDateTime startDateTime, LocalDateTime endDateTime, TravelType type, Customer customer) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.source = source;
        this.destination = destination;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.type = type;
        this.customer = customer;
    }

    public enum TravelType {
        ONE_WAY, RETURN, MULTI
    }

    public enum TravelStatus {
        ON_GOING, COMPLETED, CANCELLED;
    }

    @AssertTrue(message = "A data de início deve ser anterior à data de término!")
    public boolean isStartDateBeforeEndDate() {
        return startDateTime != null && endDateTime != null && startDateTime.isBefore(endDateTime);
    }

    // -------------- GETTERS E SETTERS --------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public TravelType getType() {
        return type;
    }

    public void setType(TravelType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
