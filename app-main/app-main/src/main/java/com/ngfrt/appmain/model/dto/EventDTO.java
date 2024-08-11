package com.ngfrt.appmain.model.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class EventDTO {

    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private String organizer;
    private String phoneNumber;
    private int tickets;
    private UUID hallId;
    private UUID userId;

    public String getDateString() {
        String monthName = getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = getDate().getDayOfMonth();
        int year = getDate().getYear();
        return String.format("%s %d, %d",monthName, day, year);
    }

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

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
