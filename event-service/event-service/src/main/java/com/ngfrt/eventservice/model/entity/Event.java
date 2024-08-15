package com.ngfrt.eventservice.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private int tickets;
    private boolean featured;
    private String phoneNumber;
    private String organizer;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID hallId;
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;
    private boolean soldOut;


    public Long getId() {
        return id;
    }

    public Event setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Event setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Event setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getTickets() {
        return tickets;
    }

    public Event setTickets(int tickets) {
        this.tickets = tickets;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Event setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public Event setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public boolean isFeatured() {
        return featured;
    }

    public Event setFeatured(boolean featured) {
        this.featured = featured;
        return this;
    }

    public UUID getHallId() {
        return hallId;
    }

    public Event setHallId(UUID hallId) {
        this.hallId = hallId;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public Event setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public Event setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
        return this;
    }
}

