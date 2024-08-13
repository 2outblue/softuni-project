package com.ngfrt.appmain.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "tickets")
public class Ticket extends BaseEntity {

    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String eventName;
    private LocalDate eventDate;
    private String email;
    private String hallName;

    @ManyToOne
    private Hall hall;

    public UUID getUuid() {
        return uuid;
    }

    public Ticket setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Ticket setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Ticket setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public Ticket setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Ticket setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Ticket setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public Ticket setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public Hall getHall() {
        return hall;
    }

    public Ticket setHall(Hall hall) {
        this.hall = hall;
        return this;
    }
}
