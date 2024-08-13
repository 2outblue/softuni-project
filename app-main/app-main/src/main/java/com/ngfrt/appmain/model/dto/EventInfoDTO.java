package com.ngfrt.appmain.model.dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class EventInfoDTO {

    private UUID uuid;
    private String description;
    private String name;
    private LocalDate date;
    private String organizer;
    private String hallName;
    private UUID hallUuid;
    private boolean soldOut;

    public String getDateString() {
        String monthName = getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int day = getDate().getDayOfMonth();
        int year = getDate().getYear();
        return String.format("%s %d, %d",monthName, day, year);
    }

    public String getUuidString() {
        return this.uuid.toString();
    }

    public UUID getUuid() {
        return uuid;
    }

    public EventInfoDTO setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventInfoDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public EventInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public EventInfoDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getOrganizer() {
        return organizer;
    }

    public EventInfoDTO setOrganizer(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public String getHallName() {
        return hallName;
    }

    public EventInfoDTO setHallName(String hallName) {
        this.hallName = hallName;
        return this;
    }

    public UUID getHallUuid() {
        return hallUuid;
    }

    public EventInfoDTO setHallUuid(UUID hallUuid) {
        this.hallUuid = hallUuid;
        return this;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public EventInfoDTO setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
        return this;
    }
}
