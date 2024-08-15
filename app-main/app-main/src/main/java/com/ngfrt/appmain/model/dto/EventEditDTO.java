package com.ngfrt.appmain.model.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class EventEditDTO {
    private LocalDate date;
    private UUID uuid;

    private String name;
    private String description;
    private String organizer;
    private String phoneNumber;
    private int tickets;
    private String hallName;

    public LocalDate getDate() {
        return date;
    }

    public EventEditDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public EventEditDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventEditDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventEditDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public EventEditDTO setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EventEditDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getTickets() {
        return tickets;
    }

    public EventEditDTO setTickets(int tickets) {
        this.tickets = tickets;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public EventEditDTO setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public String getDateString() {
        String monthName = getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = getDate().getDayOfMonth();
        int year = getDate().getYear();
        return String.format("%s %d, %d",monthName, day, year);
    }
}
