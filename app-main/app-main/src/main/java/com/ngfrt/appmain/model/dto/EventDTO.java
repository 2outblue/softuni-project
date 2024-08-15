package com.ngfrt.appmain.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class EventDTO {

    private UUID uuid;

    @NotBlank(message = "Please enter a description")
    @Size(min = 8, max = 150, message = "Description must be between 8 and 150 characters")
    private String description;

    @NotBlank(message = "Please enter a name")
    @Size(min = 5, max = 30, message = "Event name must be between 5 and 30 characters")
    private String name;

    private LocalDate date;

    @NotBlank(message = "Please specify event organizer")
    @Size(min = 2, max = 35, message = "Organizer must be between 2 and 35 characters")
    private String organizer;

    @NotBlank(message = "Phone is required")
    @Size(min = 5, max = 20, message = "Contact phone must be between 5 and 20 characters")
    private String phoneNumber;
    private int tickets;
    private UUID hallId;
    private UUID userId;
    private boolean soldOut;

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

    public boolean isSoldOut() {
        return soldOut;
    }

    public EventDTO setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
        return this;
    }
}
