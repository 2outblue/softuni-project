package com.ngfrt.eventservice.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class EventDTO {

    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private String organizer;
    private int tickets;
    private boolean featured;
    private String phoneNumber;
    private UUID hallId;
    private UUID userId;
    private boolean soldOut;

    public UUID getUuid() {
        return uuid;
    }

    public EventDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public EventDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public EventDTO setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public int getTickets() {
        return tickets;
    }

    public EventDTO setTickets(int tickets) {
        this.tickets = tickets;
        return this;
    }

    public boolean isFeatured() {
        return featured;
    }

    public EventDTO setFeatured(boolean featured) {
        this.featured = featured;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EventDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UUID getHallId() {
        return hallId;
    }

    public EventDTO setHallId(UUID hallId) {
        this.hallId = hallId;
        return this;
    }

    public UUID getUserId() {
        return userId;
    }

    public EventDTO setUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public EventDTO setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
        return this;
    }
}

