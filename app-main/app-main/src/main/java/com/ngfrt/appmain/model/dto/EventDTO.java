package com.ngfrt.appmain.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class EventDTO {

    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private int tickets;
    private UUID hallId;
    private UUID userId;

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

    public int getTickets() {
        return tickets;
    }

    public EventDTO setTickets(int tickets) {
        this.tickets = tickets;
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
}
